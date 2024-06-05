package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Conversation;

public class DeleteConversationUseCase extends UpdateConversationUseCase {

    public DeleteConversationUseCase(ConversationRepository conversationRepository) {
        super(conversationRepository);
    }

    @Override
    public Conversation update(Conversation currentData, InputValues input) {
        return currentData.delete();
    }
}
