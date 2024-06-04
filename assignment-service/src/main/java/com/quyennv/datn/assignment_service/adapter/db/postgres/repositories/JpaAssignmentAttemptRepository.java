package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.AssignmentAttemptData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAssignmentAttemptRepository extends JpaRepository<AssignmentAttemptData, UUID> {
}
