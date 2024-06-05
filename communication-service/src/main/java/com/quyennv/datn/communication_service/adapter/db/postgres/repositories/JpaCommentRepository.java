package com.quyennv.datn.communication_service.adapter.db.postgres.repositories;

import com.quyennv.datn.communication_service.adapter.db.postgres.entities.CommentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<CommentData, UUID> {
}
