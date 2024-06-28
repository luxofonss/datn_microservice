package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.*;
import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.core.usecases.UseCase;
import com.quyennv.datn.communication_service.core.usecases.notification.ConversationCreatedNotificationUseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CreateConversationUseCase extends UseCase<CreateConversationUseCase.InputValues,
        CreateConversationUseCase.OutputValues> {
    private final ConversationRepository conversationRepository;

    private final ConversationCreatedNotificationUseCase conversationCreatedNotificationUseCase;

    public CreateConversationUseCase(ConversationRepository conversationRepository, ConversationCreatedNotificationUseCase conversationCreatedNotificationUseCase) {
        this.conversationRepository = conversationRepository;
        this.conversationCreatedNotificationUseCase = conversationCreatedNotificationUseCase;
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

        Conversation response = conversationRepository.save(data);

        CompletableFuture.runAsync(() -> {
            log.info("running");
            conversationCreatedNotificationUseCase.execute(ConversationCreatedNotificationUseCase.InputValues.builder()
                    .conversation(response)
                    .courseId(response.getTargetPlacementId().toString())
                    .build());
        }).exceptionally(e -> {
            log.error("Error while publishing notification:: {}", e.getMessage());
            return null;
        });

        return new OutputValues(response);
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
