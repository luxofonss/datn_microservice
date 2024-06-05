package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.enums.AssignmentType;
import com.quyennv.datn.assignment_service.presenter.config.annotations.ValueOfEnum;
import lombok.Builder;
import lombok.Data;

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
