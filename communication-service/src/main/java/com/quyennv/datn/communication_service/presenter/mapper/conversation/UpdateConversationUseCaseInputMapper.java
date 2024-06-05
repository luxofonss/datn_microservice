package com.quyennv.datn.communication_service.presenter.mapper.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.core.usecases.conversation.UpdateConversationInfoUseCase;
import com.quyennv.datn.communication_service.presenter.dto.conversation.UpdateConversationRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;

public class UpdateConversationUseCaseInputMapper {
    public static UpdateConversationInfoUseCase.InputValues map(String id, UpdateConversationRequest request, UserPrincipal requester) {
        return UpdateConversationInfoUseCase.InputValues.builder()
                .id(Identity.fromString(id))
                .content(request.getContent())
                .type(request.getType() != null ? ConversationType.valueOf(request.getType()) : null)
                .targetPlacementId(request.getTargetPlacementId() != null ? Identity.from(request.getTargetPlacementId()) : null)
                .userId(Identity.from(requester.getId()))
                .build();
    }
}
