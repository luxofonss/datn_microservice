package com.quyennv.datn.courseservice.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.LessonType;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UpdateCourseRequestLesson {
    String id;
    @NotBlank
    String name;
    @NotBlank(message = "type is required")
    @ValueOfEnum(enumClass = LessonType.class, message = "Invalid type. Must be one of VIDEO, ASSIGNMENT, FILE")
    LessonType type;
    @NotBlank(message = "order is required")
    Integer order;
    String description;
    String resourceId;
}
