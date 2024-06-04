package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.QuestionAnswerFeedbackData;
import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaQuestionAnswerFeedbackRepository extends JpaRepository<QuestionAnswerFeedbackData, UUID> {
}
