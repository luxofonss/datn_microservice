package com.quyennv.datn.communication_service.presenter.mapper.comment;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.usecases.comment.CreateCommentUseCase;
import com.quyennv.datn.communication_service.presenter.dto.comment.CreateCommentRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;

public class CreateCommentUseCaseInputMapper {
    public static CreateCommentUseCase.InputValues map(CreateCommentRequest request, UserPrincipal requester) {
        return CreateCommentUseCase.InputValues
                .builder()
                .content(request.getContent())
                .conversationId(Identity.from(request.getConversationId()))
                .userId(Identity.from(requester.getId()))
                .build();
    }
}
