package com.quyennv.lms.core.usecases.comment;

import com.quyennv.lms.core.domain.entities.Comment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public abstract class UpdateCommentUseCase extends UseCase<UpdateCommentUseCase.InputValues, UpdateCommentUseCase.OutputValues> {
    private final CommentRepository commentRepository;

    public UpdateCommentUseCase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Comment current = commentRepository.findById(input.getId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (!current.getUser().getId().equals(input.getRequesterId())) {
            throw new IllegalArgumentException("You are not allowed to update this comment");
        }

        return new OutputValues(commentRepository.persist(update(current, input)));
    }

    public abstract Comment update(Comment current, InputValues input);
    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity id;
        Identity requesterId;
        String content;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Comment comment;
    }
}
