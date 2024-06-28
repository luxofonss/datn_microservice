package com.quyennv.datn.assignment_service.core.usecases.assignment_attempt;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Value;

import java.util.List;

public class GetAttemptsByAssignmentId extends UseCase<
        GetAttemptsByAssignmentId.InputValues, GetAttemptsByAssignmentId.OutputValues> {

    private final AssignmentAttemptRepository assignmentAttemptRepository;

    public GetAttemptsByAssignmentId(AssignmentAttemptRepository assignmentAttemptRepository) {
        this.assignmentAttemptRepository = assignmentAttemptRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(assignmentAttemptRepository.findByAssignmentId(input.getAssignmentId(), input.getStudentId()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentId;
        Identity studentId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<AssignmentAttempt> attempt;
    }
}
