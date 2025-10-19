package com.ossman.innings_app.backend.persistence.mapper;

import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.persistence.entity.UserEntity;
import java.time.LocalDateTime;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setEmail(user.getEmail());
        entity.setEnabled(user.isEnabled());
        entity.setCreatedAt(user.getCreatedAt());
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        return User.fromPersistence(entity.getId(), entity.getUsername(),
                entity.getPasswordHash(), entity.getEmail(), entity.isEnabled(),
                entity.getCreatedAt() != null ? entity.getCreatedAt() : LocalDateTime.now());
    }
}
