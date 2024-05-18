package com.quyennv.datn.courseservice.presenter.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateCourseRequestLesson {
    @NotBlank
    String name;
    String description;
    Integer order;
    String resourceId;
}
