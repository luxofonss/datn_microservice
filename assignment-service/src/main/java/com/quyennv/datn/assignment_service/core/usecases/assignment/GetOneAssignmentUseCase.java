package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class GetOneAssignmentUseCase extends UseCase<
        GetOneAssignmentUseCase.InputValues, GetOneAssignmentUseCase.OutputValues>{
    private final AssignmentRepository assignmentRepository;

    public GetOneAssignmentUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Assignment assignment = assignmentRepository.findById(input.getAssignmentId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );

        if(checkPermission(input.getRequesterId(), assignment)) {
            return new OutputValues(assignment);
        } else {
            throw new RuntimeException("No permisison");
        }

    }

    private boolean checkPermission(Identity requesterId, Assignment assignment) {
        return true;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentId;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Assignment assignment;
    }
}
