package com.ossman.innings_app.backend.domain.service;

import com.ossman.innings_app.backend.domain.exception.UserAlreadyExistsException;
import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.domain.model.UserRegistration;
import com.ossman.innings_app.backend.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserDomainService {

    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserRegistration registration) {
        userRepository.findByUsername(registration.username())
                .ifPresent(existing -> {
                    throw new UserAlreadyExistsException(
                            "Username '%s' is already registered".formatted(existing.getUsername()));
                });

        User aggregate = User.createNew(registration.username(), registration.passwordHash(),
                registration.firstName(), registration.role());
        return userRepository.save(aggregate);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
