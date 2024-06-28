package com.quyennv.datn.assignment_service.adapter.event_publisher.repository;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;

import java.util.Optional;
import java.util.UUID;

public interface KafkaAssignmentAttemptRepository {

    Optional<AssignmentAttempt> findByQuestionAnswerId(UUID questionAnswerId);

}
