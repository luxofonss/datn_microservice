package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.usecases.UseCase;
import lombok.Value;

import java.util.List;

public class GetConversationsByParentUseCase extends UseCase<GetConversationsByParentUseCase.InputValues,
        GetConversationsByParentUseCase.OutputValues> {
    private final ConversationRepository conversationRepository;

    public GetConversationsByParentUseCase(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {

        return new OutputValues(conversationRepository.findByParentId(input.parentId));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity parentId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Conversation> conversations;
    }
}
