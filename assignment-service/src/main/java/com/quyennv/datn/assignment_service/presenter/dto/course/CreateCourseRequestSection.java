package com.quyennv.datn.assignment_service.presenter.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;

@Value
public class CreateCourseRequestSection {
    @NotBlank
    String name;
    String description;
    List<CreateCourseRequestLesson> lessons;
}
