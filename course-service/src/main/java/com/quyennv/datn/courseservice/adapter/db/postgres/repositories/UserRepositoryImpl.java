package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.UserData;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.user.UserRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public User persist(User user) {
        return jpaUserRepository.save(UserData.from(user)).fromThis();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id).map(UserData::fromThis);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }
}
