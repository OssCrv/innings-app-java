package com.ossman.innings_app.backend.controller.dto;

import com.ossman.innings_app.backend.domain.model.UserRole;

public record UserResponse(Long id, String username, String firstName, UserRole role) {
}
