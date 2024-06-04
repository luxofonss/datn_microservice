package com.quyennv.lms.presenter.rest.mapper.comment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.comment.UpdateCommentUseCase;
import com.quyennv.lms.presenter.rest.dto.UpdateCommentRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

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
