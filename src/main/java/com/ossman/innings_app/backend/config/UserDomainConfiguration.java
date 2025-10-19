package com.ossman.innings_app.backend.config;

import com.ossman.innings_app.backend.domain.repository.UserRepository;
import com.ossman.innings_app.backend.domain.service.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDomainConfiguration {

    @Bean
    UserDomainService userDomainService(UserRepository userRepository) {
        return new UserDomainService(userRepository);
    }
}
