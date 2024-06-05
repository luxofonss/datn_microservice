package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.QuestionAnswerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaQuestionAnswerRepository extends JpaRepository<QuestionAnswerData, UUID> {
    Optional<QuestionAnswerData> findByAssignmentAttemptIdAndQuestionId(UUID assignmentId, UUID questionId);
}
