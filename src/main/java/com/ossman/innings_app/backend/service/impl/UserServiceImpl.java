package com.ossman.innings_app.backend.service.impl;

import com.ossman.innings_app.backend.domain.model.User;
import com.ossman.innings_app.backend.domain.model.UserRegistration;
import com.ossman.innings_app.backend.domain.service.UserDomainService;
import com.ossman.innings_app.backend.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDomainService userDomainService;

    public UserServiceImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    @Override
    @Transactional
    public User register(UserRegistration registration) {
        return userDomainService.register(registration);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDomainService.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDomainService.findAll();
    }
}
