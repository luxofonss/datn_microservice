package com.quyennv.datn.courseservice.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseTeacher {
    private Course course;
    private User teacher;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public CourseTeacher delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }
}
