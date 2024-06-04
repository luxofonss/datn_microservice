package com.quyennv.lms.core.usecases.comment;

import com.quyennv.lms.core.domain.entities.Comment;

public class UpdateCommentInfoUseCase extends UpdateCommentUseCase{
    public UpdateCommentInfoUseCase(CommentRepository commentRepository) {
        super(commentRepository);
    }

    @Override
    public Comment update(Comment current, InputValues input) {
        Comment updateData = Comment.builder()
                .id(current.getId())
                .content(input.getContent())
                .conversation(current.getConversation())
                .user(current.getUser())
                .build();
        return current.update(updateData);
    }
}
