package com.quyennv.datn.communication_service.presenter.dto.conversation;

import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.presenter.config.annotations.ValueOfEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateConversationRequest {
    private String content;
    @ValueOfEnum(enumClass = ConversationType.class)
    private String type;
    private UUID targetPlacementId;
}
