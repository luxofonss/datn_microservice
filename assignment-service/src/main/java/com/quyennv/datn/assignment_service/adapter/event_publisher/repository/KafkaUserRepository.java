package com.quyennv.datn.assignment_service.adapter.event_publisher.repository;

import com.quyennv.datn.assignment_service.core.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface KafkaUserRepository {
    Optional<User> findById(UUID userId);

}
