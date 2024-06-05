package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentAttemptData;
import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
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
