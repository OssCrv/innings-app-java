package com.ossman.innings_app.backend.controller;

import com.ossman.innings_app.backend.controller.dto.UserDtoMapper;
import com.ossman.innings_app.backend.controller.dto.UserRequest;
import com.ossman.innings_app.backend.controller.dto.UserResponse;
import com.ossman.innings_app.backend.domain.exception.UserAlreadyExistsException;
import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.domain.model.UserRegistration;
import com.ossman.innings_app.backend.domain.model.UserRole;
import com.ossman.innings_app.backend.service.UserService;
import com.ossman.innings_app.backend.service.security.PasswordHasher;
import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordHasher passwordHasher;

    public UserController(UserService userService, PasswordHasher passwordHasher) {
        this.userService = userService;
        this.passwordHasher = passwordHasher;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        String hashedPassword = passwordHasher.hash(request.password());
        UserRole role = parseRole(request.role());
        UserRegistration registration = new UserRegistration(request.username(), hashedPassword,
                request.firstName(), role);
        User created = userService.register(registration);
        return ResponseEntity
                .created(URI.create("/api/users/" + created.getId()))
                .body(UserDtoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserDtoMapper.toResponse(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> responses = userService.findAll().stream()
                .map(UserDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private static UserRole parseRole(String rawRole) {
        if (rawRole == null || rawRole.isBlank()) {
            return UserRole.USUARIO;
        }
        try {
            return UserRole.valueOf(rawRole.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown role '%s'. Allowed values are ADMIN or USUARIO.".formatted(rawRole));
        }
    }
}
