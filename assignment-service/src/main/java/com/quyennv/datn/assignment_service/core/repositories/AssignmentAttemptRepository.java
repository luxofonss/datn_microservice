package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;

import java.util.Optional;

public interface AssignmentAttemptRepository {
    AssignmentAttempt persist(AssignmentAttempt attempt);
    Optional<AssignmentAttempt> findById(Identity id);
}
