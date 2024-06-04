package com.quyennv.lms.core.usecases.comment;

import com.quyennv.lms.core.domain.entities.Comment;

public class DeleteCommentUseCase extends UpdateCommentUseCase{
    public DeleteCommentUseCase(CommentRepository commentRepository) {
        super(commentRepository);
    }

    @Override
    public Comment update(Comment current, InputValues input) {
        return current.delete();
    }
}
