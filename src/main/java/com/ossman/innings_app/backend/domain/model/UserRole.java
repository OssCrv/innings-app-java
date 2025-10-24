package com.ossman.innings_app.backend.domain.model;

public enum UserRole {
    ADMIN,
    USUARIO;

    public static UserRole fromDatabaseValue(String value) {
        if (value == null || value.isBlank()) {
            return USUARIO;
        }
        try {
            return UserRole.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return USUARIO;
        }
    }
}
