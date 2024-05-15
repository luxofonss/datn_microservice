package com.quyennv.datn.user_service.adapter.db.postgres.repositories;

import com.quyennv.datn.user_service.adapter.db.postgres.entities.UserData;
import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.usecases.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }


    @Override
    public Optional<User> getUserById(String userId) {
        return jpaUserRepository.findById(UUID.fromString(userId)).map(UserData::fromThis);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserData::fromThis);
    }

    @Override
    public Optional<User> findByEmailOrUsernameOrPhoneNumber(String username, String email, String phoneNumber) {
        return jpaUserRepository.findByEmailOrUsernameOrPhoneNumber(username, email, phoneNumber).map(UserData::fromThis);
    }

    @Override
    public User persist(User user) {
        return jpaUserRepository.save(UserData.from(user)).fromThis();
    }
}
