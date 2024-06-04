package com.quyennv.lms.core.usecases.conversation;

import com.quyennv.lms.core.domain.entities.*;

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
                .lesson(Lesson.builder().id(input.getLessonId()).build())
                .user(User.builder().id(input.getUserId()).build())
                .course(Course.builder().id(input.getCourseId()).build())
                .build();

        return currentData.update(updateData);
    }

}
