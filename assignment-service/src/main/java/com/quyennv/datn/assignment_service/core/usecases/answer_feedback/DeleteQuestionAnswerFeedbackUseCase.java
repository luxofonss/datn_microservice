package com.quyennv.datn.assignment_service.core.usecases.answer_feedback;

import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerFeedbackRepository;
import com.quyennv.datn.assignment_service.core.repositories.UpdateQuestionAnswerFeedbackUseCase;

public class DeleteQuestionAnswerFeedbackUseCase extends UpdateQuestionAnswerFeedbackUseCase {
    public DeleteQuestionAnswerFeedbackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        super(questionAnswerFeedbackRepository);
    }

    @Override
    public QuestionAnswerFeedback update(QuestionAnswerFeedback currentFeedback, InputValues input) {
        return currentFeedback.delete();
    }
}
