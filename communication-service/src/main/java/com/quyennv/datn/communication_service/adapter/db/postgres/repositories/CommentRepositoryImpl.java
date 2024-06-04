package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.CommentData;
import com.quyennv.lms.core.domain.entities.Comment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.comment.CommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;

    public CommentRepositoryImpl(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    @Override
    @Transactional
    public Comment persist(Comment comment) {
        return jpaCommentRepository.save(CommentData.from(comment)).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Identity id) {
        return jpaCommentRepository.findById(id.getId()).map(CommentData::fromThis);
    }
}
