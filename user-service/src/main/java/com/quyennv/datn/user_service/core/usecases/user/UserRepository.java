package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(String userId);
    Optional<User> getByUsername(String username);
    Optional<User> findByEmailOrUsernameOrPhoneNumber(String username, String email, String phoneNumber);
    User persist(User user);
    List<User> getUsersWithConditions(
            List<String> emails,
            List<Identity> userIds,
            String keyword);
}
