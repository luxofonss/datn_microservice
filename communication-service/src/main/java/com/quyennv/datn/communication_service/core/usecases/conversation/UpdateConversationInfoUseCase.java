package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.*;

public class UpdateConversationInfoUseCase extends UpdateConversationUseCase {

    public UpdateConversationInfoUseCase(ConversationRepository conversationRepository) {
        super(conversationRepository);
    }
    @Override
    public Conversation update(Conversation currentData, InputValues input) {
        Conversation updateData = Conversation
                .builder()
                .id(currentData.getId())
                .content(input.getContent())
                .type(input.getType())
                .user(User.builder().id(input.getUserId()).build())
                .targetPlacementId(input.getTargetPlacementId())
                .build();

        return currentData.update(updateData);
    }

}
