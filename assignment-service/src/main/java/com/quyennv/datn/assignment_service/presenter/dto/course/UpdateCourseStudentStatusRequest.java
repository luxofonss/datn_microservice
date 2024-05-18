package com.quyennv.datn.assignment_service.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseStudentStatusRequest {
    @ValueOfEnum(enumClass = EnrollStatus.class, message = "Invalid status")
    private String status;
}
