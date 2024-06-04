package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UpdateAssignmentRequest {
    String id;
    String title;
    String description;
    Identity subjectId;
    Long duration;
    String startTime;
    String endTime;
    @ValueOfEnum(enumClass = AssignmentType.class)
    String assignmentType;
    Long maxAttemptTimes;
    List<AssignmentQuestionsMutationRequest> questions;
}
