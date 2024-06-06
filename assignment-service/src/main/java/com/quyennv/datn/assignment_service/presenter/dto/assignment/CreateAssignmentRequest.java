package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.enums.AssignmentType;
import com.quyennv.datn.assignment_service.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;

@Value
public class CreateAssignmentRequest {
    @NotBlank
    String title;
    String description;
    Identity subjectId;
    List<AssignmentQuestionsMutationRequest> questions;
    Long maxAttemptTimes;
    Long duration;
    String startTime;
    String endTime;
    @ValueOfEnum(enumClass = AssignmentType.class)
    String assignmentType;
    String lessonId;
    String courseId;
}
