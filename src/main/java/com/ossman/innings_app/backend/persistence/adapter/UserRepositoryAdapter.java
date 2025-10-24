package com.ossman.innings_app.backend.persistence.adapter;

import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.domain.repository.UserRepository;
import com.ossman.innings_app.backend.persistence.entity.UserEntity;
import com.ossman.innings_app.backend.persistence.mapper.UserMapper;
import com.ossman.innings_app.backend.persistence.repository.JpaUserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity saved = jpaUserRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }
}
