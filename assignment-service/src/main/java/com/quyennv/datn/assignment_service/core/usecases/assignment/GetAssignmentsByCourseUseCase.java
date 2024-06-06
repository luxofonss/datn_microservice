package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

public class GetAssignmentsByCourseUseCase extends UseCase<
        GetAssignmentsByCourseUseCase.InputValues, GetAssignmentsByCourseUseCase.OutputValues>{
    private final AssignmentRepository assignmentRepository;

    public GetAssignmentsByCourseUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
       return new OutputValues(assignmentRepository.findAllWithFilter(
               null,
               null,
               null,
               input.getCourseId(),
               null,
               null
       ));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity requesterId;
        Identity courseId;
    }

    @Value
    @Getter
    public static class OutputValues implements UseCase.OutputValues {
        List<Assignment> assignments;
    }
}
