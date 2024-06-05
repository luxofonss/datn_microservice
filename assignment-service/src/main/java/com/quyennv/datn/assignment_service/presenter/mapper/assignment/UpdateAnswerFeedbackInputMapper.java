package com.quyennv.datn.assignment_service.presenter.mapper.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.UpdateQuestionAnswerFeedbackUseCase;
import com.quyennv.datn.assignment_service.presenter.dto.assignment.FeedbackAnswerRequest;

public class UpdateAnswerFeedbackInputMapper {
    public static UpdateQuestionAnswerFeedbackUseCase.InputValues map(String feedbackId, FeedbackAnswerRequest request, String requesterId) {
        System.out.println("feedbackId: " + feedbackId);
        return UpdateQuestionAnswerFeedbackUseCase.InputValues.builder()
                .message(request.getMessage())
                .feedbackId(Identity.fromString(feedbackId))
                .requesterId(Identity.fromString(requesterId))
                .build();
    }
}
