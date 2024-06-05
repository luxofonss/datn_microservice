package com.quyennv.datn.assignment_service.core.usecases.answer_feedback;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswer;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionAnswerFeedbackType;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerFeedbackRepository;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class TeacherAddQuestionFeedBackUseCase extends UseCase<TeacherAddQuestionFeedBackUseCase.InputValues,
        TeacherAddQuestionFeedBackUseCase.OutputValues> {
    private final QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public TeacherAddQuestionFeedBackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository,
                                             QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerFeedbackRepository = questionAnswerFeedbackRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        QuestionAnswer questionAnswer = questionAnswerRepository.findById(input.getAnswerId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Question answer not found"));

        QuestionAnswerFeedback feedback = QuestionAnswerFeedback
                .builder()
                .id(input.getFeedbackId())
                .answer(questionAnswer)
                .creatorId(input.getRequesterId())
                .message(input.getMessage())
                .type(input.getType())
                .build();
        return new OutputValues(questionAnswerFeedbackRepository.persist(feedback));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity feedbackId;
        Identity answerId;
        String message;
        Identity requesterId;
        QuestionAnswerFeedbackType type;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        QuestionAnswerFeedback feedback;
    }
}
