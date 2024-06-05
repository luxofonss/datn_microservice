package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public abstract class UpdateQuestionAnswerFeedbackUseCase extends UseCase<UpdateQuestionAnswerFeedbackUseCase.InputValues,
        UpdateQuestionAnswerFeedbackUseCase.OutputValues> {
    private final QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository;

    public UpdateQuestionAnswerFeedbackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        this.questionAnswerFeedbackRepository = questionAnswerFeedbackRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        QuestionAnswerFeedback currentFeedback = questionAnswerFeedbackRepository.findById(input.getFeedbackId())
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));
        QuestionAnswerFeedback feedback = update(currentFeedback, input);
        return new OutputValues(questionAnswerFeedbackRepository.persist(feedback));
    }

    public abstract QuestionAnswerFeedback update(QuestionAnswerFeedback currentFeedback, InputValues input);
    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity feedbackId;
        String message;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        QuestionAnswerFeedback feedback;
    }
}
