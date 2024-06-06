package com.quyennv.datn.courseservice.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseStudent {
    private Course course;
    private Identity studentId;
    private User student;
    private Integer price;
    private EnrollStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public CourseStudent withStatus(EnrollStatus status) {
        this.status = status;
        return this;
    }

    public CourseStudent delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }
}
