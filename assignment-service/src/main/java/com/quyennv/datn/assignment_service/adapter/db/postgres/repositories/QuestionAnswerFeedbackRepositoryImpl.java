package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.QuestionAnswerFeedbackData;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerFeedbackRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class QuestionAnswerFeedbackRepositoryImpl implements QuestionAnswerFeedbackRepository {
    private final JpaQuestionAnswerFeedbackRepository jpaQuestionAnswerFeedbackRepository;

    public QuestionAnswerFeedbackRepositoryImpl(JpaQuestionAnswerFeedbackRepository jpaQuestionAnswerFeedbackRepository) {
        this.jpaQuestionAnswerFeedbackRepository = jpaQuestionAnswerFeedbackRepository;
    }

    @Override
    @Transactional
    public QuestionAnswerFeedback persist(QuestionAnswerFeedback feedback) {
        return jpaQuestionAnswerFeedbackRepository.save(QuestionAnswerFeedbackData.from(feedback)).fromThis();
    }

    @Override
    @Transactional
    public QuestionAnswerFeedback delete(QuestionAnswerFeedback feedback) {
        jpaQuestionAnswerFeedbackRepository.delete(QuestionAnswerFeedbackData.from(feedback));
        return feedback;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionAnswerFeedback> findById(Identity id) {
        return jpaQuestionAnswerFeedbackRepository.findById(id.getId()).map(QuestionAnswerFeedbackData::fromThis);
    }
}
