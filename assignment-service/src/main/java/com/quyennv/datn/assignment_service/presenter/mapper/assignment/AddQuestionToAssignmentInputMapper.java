package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class AddQuestionToAssignmentInputMapper extends UpdateAssignmentDetailUseCaseRequestMapper {
    public static UpdateAssignmentDetailUseCase.InputValues map(UserPrincipal requester, UpdateAssignmentRequest req, String assignmentId) {
        return UpdateAssignmentDetailUseCase.InputValues
                .builder()
                .assignmentId(Identity.fromString(assignmentId))
                .teacherId(Identity.from(requester.getId()))
                .questions(mapQuestions(req.getQuestions()))
                .build();
    }
}
