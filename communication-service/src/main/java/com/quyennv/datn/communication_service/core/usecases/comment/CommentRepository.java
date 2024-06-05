package com.quyennv.datn.communication_service.core.usecases.comment;

import com.quyennv.datn.communication_service.core.domain.entities.Comment;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;

import java.util.Optional;

public interface CommentRepository {
    Comment persist(Comment comment);
    Optional<Comment> findById(Identity id);
}
