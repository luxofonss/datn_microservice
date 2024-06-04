package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.usecases.UseCase;
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
