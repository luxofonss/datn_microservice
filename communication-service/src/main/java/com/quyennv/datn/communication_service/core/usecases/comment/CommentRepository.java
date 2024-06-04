package com.quyennv.lms.core.usecases.comment;

import com.quyennv.lms.core.domain.entities.Comment;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.Optional;

public interface CommentRepository {
    Comment persist(Comment comment);
    Optional<Comment> findById(Identity id);
}
