package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public abstract class GetAssignmentsUseCase extends UseCase<
        GetAssignmentsUseCase.InputValues, GetAssignmentsUseCase.OutputValues>{
    public final AssignmentRepository assignmentRepository;

    protected GetAssignmentsUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Assignment> assignments = getAssignments(input);
        return new OutputValues(assignments);
    }

    public abstract List<Assignment> getAssignments(InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String title;
        Identity teacherId;
        Identity subjectId;
        Identity courseId;
        Identity lessonId;
        Identity studentId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Assignment> assignments;
    }
}
