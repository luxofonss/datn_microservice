package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentAttemptData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAssignmentAttemptRepository extends JpaRepository<AssignmentAttemptData, UUID> {
    List<AssignmentAttemptData> findByAssignmentIdAndStudentId(UUID assignmentId, UUID studentId);

    @Query("SELECT a FROM assignment_attempts a WHERE a.id = (SELECT qa.assignmentAttempt.id FROM question_answer qa WHERE qa.id = :questionAnswerId)")
    Optional<AssignmentAttemptData> findByQuestionAnswerId(@Param("questionAnswerId") UUID questionAnswerId);

}
