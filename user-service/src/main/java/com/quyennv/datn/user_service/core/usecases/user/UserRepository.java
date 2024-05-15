package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(String userId);
    Optional<User> getByUsername(String username);
    Optional<User> findByEmailOrUsernameOrPhoneNumber(String username, String email, String phoneNumber);
    User persist(User user);
}
