package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.CommentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<CommentData, UUID> {
}
