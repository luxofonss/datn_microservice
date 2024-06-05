package com.quyennv.datn.assignment_service.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionAnswer {
    private Identity id;
    private String textAnswer;
    private String teacherFixedTextAnswer;
    private Integer score;
    private Identity creatorId;
    private Question question;
    private Set<QuestionChoice> selectedOptions;
    private List<QuestionAnswerFeedback> feedbacks;
    private AssignmentAttempt assignmentAttempt;
    private Identity assignmentAttemptId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void update(QuestionAnswer newAnswer) {
        this.textAnswer = newAnswer.textAnswer;
        this.teacherFixedTextAnswer = newAnswer.teacherFixedTextAnswer;
        this.selectedOptions = newAnswer.selectedOptions;
    }
}
