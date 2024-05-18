package com.quyennv.datn.assignment_service.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.Null;
import lombok.Value;

import java.util.List;

@Value
public class GetCoursesRequest {
    String keyword;
    @Null
    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;
    Integer grade;
    String code;
    List<String> teacherIds;
}
