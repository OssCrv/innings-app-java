package com.ossman.innings_app.backend.service.security;

public interface PasswordHasher {

    String hash(String rawPassword);
}
