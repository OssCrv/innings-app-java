package com.ossman.innings_app.backend.domain.model;

import java.util.Objects;

public record UserRegistration(String username, String passwordHash, String firstName,
        UserRole role) {

    public UserRegistration {
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(passwordHash, "passwordHash");
    }
}
