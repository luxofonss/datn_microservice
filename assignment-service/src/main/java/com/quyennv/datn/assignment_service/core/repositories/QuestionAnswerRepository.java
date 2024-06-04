package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.QuestionAnswer;

import java.util.Optional;
import java.util.UUID;

public interface QuestionAnswerRepository {
    QuestionAnswer persist(QuestionAnswer questionAnswer);
    Optional<QuestionAnswer> findByAssignmentAttemptAndQuestion(UUID assignmentAttemptId, UUID questionId);
    Optional<QuestionAnswer> findById(UUID id);
    void delete(QuestionAnswer questionAnswer);
}
