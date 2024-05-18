package com.quyennv.datn.assignment_service.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.enums.LessonStudentStatus;
import com.quyennv.datn.courseservice.core.domain.valueobject.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LessonStudent {
    private Lesson lesson;
    private User student;
    private LessonStudentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public LessonStudent delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }

    public Identity getLessonId() {
        return lesson.getId();
    }

    public Identity getStudentId() {
        return student.getId();
    }
}
