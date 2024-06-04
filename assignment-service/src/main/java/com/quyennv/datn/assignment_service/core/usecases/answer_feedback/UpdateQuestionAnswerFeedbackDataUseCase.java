package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.lms.core.domain.entities.User;

public class UpdateQuestionAnswerFeedbackDataUseCase extends UpdateQuestionAnswerFeedbackUseCase {
    public UpdateQuestionAnswerFeedbackDataUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        super(questionAnswerFeedbackRepository);
    }

    @Override
    public QuestionAnswerFeedback update(QuestionAnswerFeedback currentFeedback, InputValues input) {
        QuestionAnswerFeedback feedback = QuestionAnswerFeedback.builder()
                .id(currentFeedback.getId())
                .message(input.getMessage())
                .creator(User.builder().id(input.getRequesterId()).build())
                .build();
        return currentFeedback.update(feedback);
    }
}
