package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.QuestionAnswerData;
import com.quyennv.lms.core.domain.entities.QuestionAnswer;
import com.quyennv.lms.core.usecases.assignment.QuestionAnswerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class QuestionAnswerRepositoryImpl implements QuestionAnswerRepository {
    private final JpaQuestionAnswerRepository jpaQuestionAnswerRepository;

    public QuestionAnswerRepositoryImpl(JpaQuestionAnswerRepository jpaQuestionAnswerRepository) {
        this.jpaQuestionAnswerRepository = jpaQuestionAnswerRepository;
    }

    @Override
    @Transactional
    public QuestionAnswer persist(QuestionAnswer questionAnswer) {
        return jpaQuestionAnswerRepository.save(QuestionAnswerData.from(questionAnswer)).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionAnswer> findByAssignmentAttemptAndQuestion(UUID assignmentId, UUID questionId) {
        return jpaQuestionAnswerRepository.findByAssignmentAttemptIdAndQuestionId(
                assignmentId,
                questionId)
                .map(QuestionAnswerData::fromThis);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionAnswer> findById(UUID id) {
        return jpaQuestionAnswerRepository.findById(id).map(QuestionAnswerData::fromThis);
    }

    @Override
    @Transactional
    public void delete(QuestionAnswer questionAnswer) {
        jpaQuestionAnswerRepository.delete(QuestionAnswerData.from(questionAnswer));
    }
}
