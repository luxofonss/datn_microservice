package com.quyennv.lms.core.usecases.conversation;

import com.quyennv.lms.core.domain.entities.*;
import com.quyennv.lms.core.domain.enums.ConversationType;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public abstract class UpdateConversationUseCase extends UseCase<UpdateConversationUseCase.InputValues,
        UpdateConversationUseCase.OutputValues>{
    private final ConversationRepository conversationRepository;

    public UpdateConversationUseCase(ConversationRepository conversationRepository){
            this.conversationRepository=conversationRepository;
            }

    @Override
    public OutputValues execute(InputValues input){
            Conversation current=conversationRepository.findById(input.getId())
            .orElseThrow(()->new IllegalArgumentException("Conversation not found"));
            return new OutputValues(conversationRepository.save(update(current, input)));
            }

    public abstract Conversation update(Conversation currentData, InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity id;
        String content;
        ConversationType type;
        Identity lessonId;
        Identity userId;
        Identity courseId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Conversation conversation;
    }
}