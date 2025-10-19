package com.ossman.innings_app.backend.controller.dto;

import java.time.LocalDateTime;

public record UserResponse(Long id, String username, String email, boolean enabled,
        LocalDateTime createdAt) {
}
