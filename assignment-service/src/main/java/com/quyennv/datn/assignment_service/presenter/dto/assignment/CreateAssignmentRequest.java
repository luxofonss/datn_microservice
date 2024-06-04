package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
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
}
