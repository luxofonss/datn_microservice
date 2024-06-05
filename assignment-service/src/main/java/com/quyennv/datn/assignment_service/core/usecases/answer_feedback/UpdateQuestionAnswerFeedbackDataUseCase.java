package com.quyennv.datn.assignment_service.core.usecases.answer_feedback;

import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerFeedbackRepository;
import com.quyennv.datn.assignment_service.core.repositories.UpdateQuestionAnswerFeedbackUseCase;

public class UpdateQuestionAnswerFeedbackDataUseCase extends UpdateQuestionAnswerFeedbackUseCase {
    public UpdateQuestionAnswerFeedbackDataUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        super(questionAnswerFeedbackRepository);
    }

    @Override
    public QuestionAnswerFeedback update(QuestionAnswerFeedback currentFeedback, InputValues input) {
        QuestionAnswerFeedback feedback = QuestionAnswerFeedback.builder()
                .id(currentFeedback.getId())
                .message(input.getMessage())
                .creatorId(input.getRequesterId())
                .build();
        return currentFeedback.update(feedback);
    }
}
