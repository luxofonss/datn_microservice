package com.quyennv.datn.courseservice.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseInfo {
    private Identity id;

    private Course course;
    private String content;
    private CourseInfoType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
