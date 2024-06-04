package com.quyennv.lms.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssignmentAttempt {
    private Identity id;
    private User student;
    private Assignment assignment;
    private List<QuestionAnswer> answers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime submittedAt;
    private String teacherComment;
    private Integer totalMark;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public List<Question> getQuestions() {
        if (assignment != null)
            return assignment.getQuestions();
        else
            return new ArrayList<>();
    }

    public AssignmentAttempt submit() {
        this.submittedAt = LocalDateTime.now();
        return this;
    }

    public Identity getStudentId() {
        if (student != null)
            return student.getId();
        return null;
    }
}
