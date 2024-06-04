package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionAnswer;
import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.domain.enums.QuestionAnswerFeedbackType;
import com.quyennv.lms.core.usecases.UseCase;
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
    public TeacherAddQuestionFeedBackUseCase.OutputValues execute(TeacherAddQuestionFeedBackUseCase.InputValues input) {
        QuestionAnswer questionAnswer = questionAnswerRepository.findById(input.getAnswerId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Question answer not found"));

        QuestionAnswerFeedback feedback = QuestionAnswerFeedback
                .builder()
                .id(input.getFeedbackId())
                .answer(questionAnswer)
                .creator(User.builder().id(input.getRequesterId()).build())
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
