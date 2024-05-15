package com.quyennv.datn.courseservice.presenter.config;

import com.quyennv.datn.courseservice.presenter.usecases.security.JwtAuthenticationEntryPoint;
import com.quyennv.datn.courseservice.presenter.usecases.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
//    private final AuthenticationProvider authProvider;
    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint unAuthorizedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(unAuthorizedHandler))
                .authorizeHttpRequests(auth ->
                        auth
//                                .requestMatchers("/users/auth/login", "/users/auth/register")
//                                .permitAll()
                                .anyRequest()
                                .permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
//                .authenticationProvider(authProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .securityContext(ctx -> ctx.requireExplicitSave(false));

        return http.build();
    }
}
