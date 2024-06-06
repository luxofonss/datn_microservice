package com.quyennv.datn.communication_service.adapter.event_consumer.kafka.cdc.user;

import com.quyennv.datn.communication_service.core.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User persist(User user);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
}
