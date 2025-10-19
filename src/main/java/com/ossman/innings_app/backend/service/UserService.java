package com.ossman.innings_app.backend.service;

import com.ossman.innings_app.backend.persistence.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
