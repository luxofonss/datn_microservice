package com.quyennv.datn.user_service.adapter.db.postgres.repositories;

import com.quyennv.datn.user_service.adapter.db.postgres.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByEmail(String email);
    Optional<UserData> findByUsername(String username);

    Optional<UserData> findByEmailOrUsernameOrPhoneNumber(String email, String username, String phoneNumber);
    List<UserData> findAllByEmailIn(List<String> emails);
}
