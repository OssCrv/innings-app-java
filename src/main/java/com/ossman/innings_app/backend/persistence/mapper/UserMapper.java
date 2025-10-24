package com.ossman.innings_app.backend.persistence.mapper;

import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.persistence.entity.UserEntity;
public final class UserMapper {

    private UserMapper() {
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setFirstName(user.getFirstName());
        entity.setRole(user.getRole().name());
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        return User.fromPersistence(entity.getId(), entity.getUsername(),
                entity.getPasswordHash(), entity.getFirstName(), entity.getRole());
    }
}
