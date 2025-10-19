package com.ossman.innings_app.backend.service.security;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class NoOpPasswordHasher implements PasswordHasher {

    @Override
    public String hash(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password must not be blank");
        }
        // Provide a deterministic but simple hashing strategy using MD5 to avoid
        // storing clear text passwords while keeping the example lightweight.
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes());
    }
}
