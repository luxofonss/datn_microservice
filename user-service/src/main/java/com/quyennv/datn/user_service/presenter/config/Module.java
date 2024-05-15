package com.quyennv.datn.user_service.presenter.config;

import com.quyennv.datn.user_service.core.usecases.user.CreateUserUseCase;
import com.quyennv.datn.user_service.core.usecases.user.GetAuthProfileUseCase;
import com.quyennv.datn.user_service.core.usecases.user.UserRepository;
import com.quyennv.datn.user_service.presenter.usecases.security.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    AppUserDetailsService userDetailsHrmsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    GetAuthProfileUseCase getAuthProfileUseCase(UserRepository userRepository) {
        return new GetAuthProfileUseCase(userRepository);
    }
}
