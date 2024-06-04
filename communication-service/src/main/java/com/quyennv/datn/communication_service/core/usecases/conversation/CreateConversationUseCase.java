package com.quyennv.lms.core.usecases.conversation;

import com.quyennv.lms.core.domain.entities.*;
import com.quyennv.lms.core.domain.enums.ConversationType;
import com.quyennv.lms.core.usecases.UseCase;
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
                .lesson(input.getLessonId() != null ? Lesson.builder().id(input.getLessonId()).build() : null)
                .user(input.getUserId() != null ? User.builder().id(input.getUserId()).build() : null)
                .course(input.getCourseId() != null ? Course.builder().id(input.getCourseId()).build() : null)
                .build();
        return new OutputValues(conversationRepository.save(data));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
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
