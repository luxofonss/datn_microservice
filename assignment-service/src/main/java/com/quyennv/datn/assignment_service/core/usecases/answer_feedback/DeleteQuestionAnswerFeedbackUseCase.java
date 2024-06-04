package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;

public class DeleteQuestionAnswerFeedbackUseCase extends UpdateQuestionAnswerFeedbackUseCase{
    public DeleteQuestionAnswerFeedbackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        super(questionAnswerFeedbackRepository);
    }

    @Override
    public QuestionAnswerFeedback update(QuestionAnswerFeedback currentFeedback, InputValues input) {
        return currentFeedback.delete();
    }
}
