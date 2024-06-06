package com.quyennv.datn.communication_service.core.usecases.comment;

import com.quyennv.datn.communication_service.core.domain.entities.Comment;
import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.domain.entities.User;
import com.quyennv.datn.communication_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class CreateCommentUseCase extends UseCase<CreateCommentUseCase.InputValues, CreateCommentUseCase.OutputValues> {
    private final CommentRepository commentRepository;

    public CreateCommentUseCase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Comment comment = Comment.builder()
                .content(input.getContent())
                .conversation(Conversation.builder().id(input.getConversationId()).build())
                .user(User.builder().id(input.getUserId()).build())
                .build();
        return new OutputValues(commentRepository.persist(comment));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String content;
        Identity conversationId;
        Identity userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Comment comment;
    }
}
