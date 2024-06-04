package com.quyennv.lms.presenter.rest.mapper.comment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.comment.CreateCommentUseCase;
import com.quyennv.lms.presenter.rest.dto.CreateCommentRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

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
