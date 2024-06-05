package com.quyennv.datn.assignment_service.presenter.mapper.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.usecases.answer_feedback.TeacherAddQuestionFeedBackUseCase;
import com.quyennv.datn.assignment_service.presenter.dto.assignment.FeedbackAnswerRequest;

public class AddAnswerFeedbackInputMapper {
    public static TeacherAddQuestionFeedBackUseCase.InputValues createInput(FeedbackAnswerRequest request, String requesterId, String answerId) {
        return TeacherAddQuestionFeedBackUseCase.InputValues.builder()
                .feedbackId(Identity.fromString(request.getId()))
                .requesterId(Identity.fromString(requesterId))
                .answerId(Identity.fromString(answerId))
                .message(request.getMessage())
                .type(request.getType())
                .build();
    }
}
