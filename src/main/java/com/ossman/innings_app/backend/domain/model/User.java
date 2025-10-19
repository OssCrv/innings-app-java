package com.ossman.innings_app.backend.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Aggregate root representing a user within the domain layer.
 */
public final class User {

    private final Long id;
    private final String username;
    private final String passwordHash;
    private final String email;
    private final boolean enabled;
    private final LocalDateTime createdAt;

    private User(Long id, String username, String passwordHash, String email, boolean enabled,
            LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public static User createNew(String username, String passwordHash, String email) {
        return new User(null, validateUsername(username), validatePassword(passwordHash),
                validateEmail(email), true, LocalDateTime.now());
    }

    public static User fromPersistence(Long id, String username, String passwordHash, String email,
            boolean enabled, LocalDateTime createdAt) {
        Objects.requireNonNull(createdAt, "createdAt");
        return new User(id, validateUsername(username), validatePassword(passwordHash),
                validateEmail(email), enabled, createdAt);
    }

    public User withId(Long id) {
        return new User(id, username, passwordHash, email, enabled, createdAt);
    }

    public User disable() {
        return new User(id, username, passwordHash, email, false, createdAt);
    }

    public User enable() {
        return new User(id, username, passwordHash, email, true, createdAt);
    }

    public User changeEmail(String newEmail) {
        return new User(id, username, passwordHash, validateEmail(newEmail), enabled, createdAt);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private static String validateUsername(String username) {
        Objects.requireNonNull(username, "username");
        String trimmed = username.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("username must not be empty");
        }
        if (trimmed.length() > 50) {
            throw new IllegalArgumentException("username must have at most 50 characters");
        }
        return trimmed;
    }

    private static String validatePassword(String passwordHash) {
        Objects.requireNonNull(passwordHash, "passwordHash");
        if (passwordHash.isBlank()) {
            throw new IllegalArgumentException("passwordHash must not be blank");
        }
        return passwordHash;
    }

    private static String validateEmail(String email) {
        Objects.requireNonNull(email, "email");
        String trimmed = email.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("email must not be empty");
        }
        if (trimmed.length() > 120) {
            throw new IllegalArgumentException("email must have at most 120 characters");
        }
        return trimmed;
    }
}
