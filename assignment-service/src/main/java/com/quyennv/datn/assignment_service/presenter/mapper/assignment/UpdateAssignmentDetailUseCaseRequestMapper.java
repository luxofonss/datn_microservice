package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;

import java.util.Objects;

public class UpdateAssignmentDetailUseCaseRequestMapper extends UpdateAssignmentUseCaseRequestMapper{
    public static UpdateAssignmentDetailUseCase.InputValues map(UserPrincipal requester, UpdateAssignmentRequest req, String assignmentId) {
        return UpdateAssignmentDetailUseCase.InputValues
                .builder()
                .assignmentId(Identity.fromString(assignmentId))
                .title(req.getTitle())
                .description(req.getDescription())
                .teacherId(Identity.from(requester.getId()))
                .subjectId(req.getSubjectId())
                .questions(mapQuestions(req.getQuestions()))
                .duration(req.getDuration())
                .startTime(Objects.nonNull(req.getStartTime()) ? DateHelper.toLocalDateTime(req.getStartTime()):null)
                .endTime(Objects.nonNull(req.getEndTime()) ? DateHelper.toLocalDateTime(req.getEndTime()):null)
                .assignmentType(Objects.nonNull(req.getAssignmentType()) ? AssignmentType.valueOf(req.getAssignmentType()) : AssignmentType.REGULAR)
                .maxAttemptTimes(req.getMaxAttemptTimes())
                .build();
    }
}
