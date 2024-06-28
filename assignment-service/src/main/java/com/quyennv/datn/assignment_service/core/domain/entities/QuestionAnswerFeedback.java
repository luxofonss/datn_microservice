package com.quyennv.datn.assignment_service.core.domain.entities;

import com.quyennv.datn.assignment_service.core.domain.enums.QuestionAnswerFeedbackType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionAnswerFeedback {
    private Identity id;

    private Identity creatorId;
    private User creator;
    private String message;
    private QuestionAnswerFeedbackType type;
    private QuestionAnswer answer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public QuestionAnswerFeedback update(QuestionAnswerFeedback feedback) {
        this.creatorId = feedback.creatorId;
        this.message = feedback.message;
        this.type = feedback.type;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public QuestionAnswerFeedback delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }
}
