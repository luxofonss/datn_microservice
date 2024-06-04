package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.UpdateQuestionAnswerFeedbackUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.FeedbackAnswerRequest;

public class UpdateAnswerFeedbackInputMapper {
    public static UpdateQuestionAnswerFeedbackUseCase.InputValues map(String feedbackId, FeedbackAnswerRequest request, String requesterId) {
        return UpdateQuestionAnswerFeedbackUseCase.InputValues.builder()
                .message(request.getMessage())
                .feedbackId(Identity.fromString(feedbackId))
                .requesterId(Identity.fromString(requesterId))
                .build();
    }
}
