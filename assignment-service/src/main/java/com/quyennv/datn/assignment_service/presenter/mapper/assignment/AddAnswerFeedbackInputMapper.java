package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.TeacherAddQuestionFeedBackUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.FeedbackAnswerRequest;

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
