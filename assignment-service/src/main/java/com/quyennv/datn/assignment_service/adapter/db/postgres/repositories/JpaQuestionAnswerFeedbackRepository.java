package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.QuestionAnswerFeedbackData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaQuestionAnswerFeedbackRepository extends JpaRepository<QuestionAnswerFeedbackData, UUID> {
}
