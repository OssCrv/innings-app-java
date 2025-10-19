package com.ossman.innings_app.backend.controller.dto;

import com.ossman.innings_app.backend.domain.model.User;

public final class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.isEnabled(), user.getCreatedAt());
    }
}
