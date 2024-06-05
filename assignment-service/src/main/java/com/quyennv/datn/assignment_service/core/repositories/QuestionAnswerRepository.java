package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswer;

import java.util.Optional;
import java.util.UUID;

public interface QuestionAnswerRepository {
    QuestionAnswer persist(QuestionAnswer questionAnswer);
    Optional<QuestionAnswer> findByAssignmentAttemptAndQuestion(UUID assignmentAttemptId, UUID questionId);
    Optional<QuestionAnswer> findById(UUID id);
    void delete(QuestionAnswer questionAnswer);
}
