package com.quyennv.datn.assignment_service.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.LessonStudentStatus;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateStudyStatusRequest {
    @ValueOfEnum(enumClass = LessonStudentStatus.class, message = "Invalid type")
    String status;
}
