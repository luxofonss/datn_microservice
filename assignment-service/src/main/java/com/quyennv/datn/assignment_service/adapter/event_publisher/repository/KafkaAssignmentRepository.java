package com.quyennv.datn.assignment_service.adapter.event_publisher.repository;


import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;

import java.util.Optional;
import java.util.UUID;

public interface KafkaAssignmentRepository {
    Optional<Assignment> getAssignmentByAttemptId(UUID assignmentAttemptId);
}
