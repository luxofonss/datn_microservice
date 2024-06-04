package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.QuestionAnswerFeedbackData;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.lms.core.usecases.assignment.QuestionAnswerFeedbackRepository;
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
