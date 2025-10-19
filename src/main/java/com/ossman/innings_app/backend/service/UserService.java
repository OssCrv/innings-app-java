package com.ossman.innings_app.backend.service;

import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.domain.model.UserRegistration;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(UserRegistration registration);

    Optional<User> findById(Long id);

    List<User> findAll();
}
