package com.quyennv.datn.assignment_service.presenter.mapper.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.datn.assignment_service.presenter.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.datn.assignment_service.presenter.usecases.security.UserPrincipal;

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
