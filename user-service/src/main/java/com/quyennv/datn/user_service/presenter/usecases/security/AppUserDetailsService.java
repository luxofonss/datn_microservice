package com.quyennv.datn.user_service.presenter.usecases.security;

import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.usecases.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );

        return UserPrincipal.from(user);
    }
}
