package com.quyennv.datn.courseservice.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import lombok.Value;

@Value
public class UpdateCourseRequestCourseStudent {
    String id;
    String studentId;
    @ValueOfEnum(enumClass = EnrollStatus.class)
    String status;
    Integer price;
}
