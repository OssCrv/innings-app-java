package com.ossman.innings_app.backend.domain.model;

import java.util.Objects;

/**
 * Aggregate root representing a user within the domain layer.
 */
public final class User {

    private final Long id;
    private final String username;
    private final String passwordHash;
    private final String firstName;
    private final UserRole role;

    private User(Long id, String username, String passwordHash, String firstName, UserRole role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.role = role;
    }

    public static User createNew(String username, String passwordHash, String firstName, UserRole role) {
        return new User(null, validateUsername(username), validatePassword(passwordHash),
                normalizeFirstName(firstName), Objects.requireNonNullElse(role, UserRole.USUARIO));
    }

    public static User fromPersistence(Long id, String username, String passwordHash, String firstName,
            String role) {
        return new User(id, validateUsername(username), validatePassword(passwordHash),
                normalizeFirstName(firstName), UserRole.fromDatabaseValue(role));
    }

    public User withId(Long id) {
        return new User(id, username, passwordHash, firstName, role);
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

    public String getFirstName() {
        return firstName;
    }

    public UserRole getRole() {
        return role;
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

    private static String normalizeFirstName(String firstName) {
        if (firstName == null) {
            return null;
        }
        String trimmed = firstName.trim();
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("firstName must have at most 100 characters");
        }
        return trimmed.isEmpty() ? null : trimmed;
    }
}
