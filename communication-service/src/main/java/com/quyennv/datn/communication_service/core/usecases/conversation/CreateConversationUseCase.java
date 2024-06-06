package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.*;
import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class CreateConversationUseCase extends UseCase<CreateConversationUseCase.InputValues,
        CreateConversationUseCase.OutputValues> {
    private final ConversationRepository conversationRepository;

    public CreateConversationUseCase(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation data = Conversation
                .builder()
                .content(input.getContent())
                .type(input.getType())
                .user(input.getUserId() != null ? User.builder().id(input.getUserId()).build() : null)
                .targetPlacementId(input.getTargetPlacementId())
                .build();
        return new OutputValues(conversationRepository.save(data));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String content;
        ConversationType type;
        Identity userId;
        Identity targetPlacementId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Conversation conversation;
    }
}
