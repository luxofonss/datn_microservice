package com.quyennv.datn.communication_service.presenter.mapper.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.core.usecases.conversation.CreateConversationUseCase;
import com.quyennv.datn.communication_service.presenter.dto.conversation.CreateConversationRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;

public class CreateConversationUseCaseInputMapper {
    public static CreateConversationUseCase.InputValues map(CreateConversationRequest request, UserPrincipal requester) {
        return CreateConversationUseCase.InputValues.builder()
                .content(request.getContent())
                .type(request.getType() != null ? ConversationType.valueOf(request.getType()) : null)
                .targetPlacementId(request.getTargetPlacementId() != null ? Identity.from(request.getTargetPlacementId()) : null)
                .userId(Identity.from(requester.getId()))
                .build();
    }
}
