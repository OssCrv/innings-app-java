package com.ossman.innings_app.backend.domain.model;

import java.util.Objects;

/**
 * Value object that captures the intent to register a new user.
 */
public record UserRegistration(String username, String passwordHash, String email) {

    public UserRegistration {
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(passwordHash, "passwordHash");
        Objects.requireNonNull(email, "email");
    }
}
