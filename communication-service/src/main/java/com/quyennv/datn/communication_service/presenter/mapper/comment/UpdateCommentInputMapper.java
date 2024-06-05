package com.quyennv.datn.communication_service.presenter.mapper.comment;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.usecases.comment.UpdateCommentUseCase;
import com.quyennv.datn.communication_service.presenter.dto.comment.UpdateCommentRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;

public class UpdateCommentInputMapper {
    public static UpdateCommentUseCase.InputValues map(String id, UpdateCommentRequest request, UserPrincipal requester) {
        return UpdateCommentUseCase.InputValues
                .builder()
                .id(Identity.fromString(id))
                .content(request.getContent())
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
