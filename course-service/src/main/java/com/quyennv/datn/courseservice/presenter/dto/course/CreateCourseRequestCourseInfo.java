package com.quyennv.datn.courseservice.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateCourseRequestCourseInfo {
    @NotBlank
    String content;
    @NotBlank
    @ValueOfEnum(enumClass = CourseInfoType.class)
    String type;
}
