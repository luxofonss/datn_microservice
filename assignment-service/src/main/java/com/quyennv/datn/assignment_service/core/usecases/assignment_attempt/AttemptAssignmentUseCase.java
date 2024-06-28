package com.quyennv.datn.assignment_service.core.usecases.assignment_attempt;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.User;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

public class AttemptAssignmentUseCase extends UseCase<
        AttemptAssignmentUseCase.InputValues, AttemptAssignmentUseCase.OutputValues> {
    private final AssignmentRepository assignmentRepository;
    private final AssignmentAttemptRepository assignmentAttemptRepository;

    public AttemptAssignmentUseCase(AssignmentRepository assignmentRepository, AssignmentAttemptRepository assignmentAttemptRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentAttemptRepository = assignmentAttemptRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Assignment assignment = assignmentRepository.findById(input.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        if (!checkPermission(assignment, input.getStudentId())) {
            throw new RuntimeException("Permission denied");
        }

        List<AssignmentAttempt> prevAttempts = assignment.getAttempts().stream().filter(a -> a.getStudent().getId().equals(input.getStudentId())).toList();

        if (prevAttempts.size() >= assignment.getMaxAttemptTimes()) {
            throw new RuntimeException("Max attempt times reached");
        }

        AssignmentAttempt attempt = AssignmentAttempt.builder()
                .assignment(assignment)
                .studentId(input.getStudentId())
                .student(User.builder().id(input.getStudentId()).build())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(assignment.getDuration()))
                .build();

        return new OutputValues(assignmentAttemptRepository.persist(attempt));
    }

    private Boolean checkPermission(Assignment assignment, Identity studentId) {
        return true;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentId;
        Identity studentId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        AssignmentAttempt attempt;
    }
}
