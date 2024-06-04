package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.Optional;

public interface AssignmentAttemptRepository {
    AssignmentAttempt persist(AssignmentAttempt attempt);
    Optional<AssignmentAttempt> findById(Identity id);
}
