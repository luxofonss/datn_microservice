package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentAttemptData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAssignmentAttemptRepository extends JpaRepository<AssignmentAttemptData, UUID> {
}
