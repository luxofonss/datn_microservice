package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.AssignmentAttemptData;
import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.AssignmentAttemptRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AssignmentAttemptRepositoryImpl implements AssignmentAttemptRepository {
    private final JpaAssignmentAttemptRepository jpaAssignmentAttemptRepository;

    public AssignmentAttemptRepositoryImpl(JpaAssignmentAttemptRepository jpaAssignmentAttemptRepository) {
        this.jpaAssignmentAttemptRepository = jpaAssignmentAttemptRepository;
    }

    @Override
    @Transactional
    public AssignmentAttempt persist(AssignmentAttempt attempt) {
        return jpaAssignmentAttemptRepository.save(AssignmentAttemptData.from(attempt)).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssignmentAttempt> findById(Identity id) {
        return jpaAssignmentAttemptRepository.findById(id.getId()).map(AssignmentAttemptData::fromThis);
    }
}
