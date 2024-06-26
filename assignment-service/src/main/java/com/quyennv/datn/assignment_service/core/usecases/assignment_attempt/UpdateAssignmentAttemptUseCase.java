package com.quyennv.datn.assignment_service.core.usecases.assignment_attempt;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

public abstract class UpdateAssignmentAttemptUseCase extends UseCase<
        UpdateAssignmentAttemptUseCase.InputValues,
        UpdateAssignmentAttemptUseCase.OutputValues> {
    private final AssignmentAttemptRepository assignmentAttemptRepository;

    protected UpdateAssignmentAttemptUseCase(AssignmentAttemptRepository assignmentAttemptRepository) {
        this.assignmentAttemptRepository = assignmentAttemptRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        AssignmentAttempt assignmentAttempt = assignmentAttemptRepository.findById(input.getAssignmentAttemptId())
                .orElseThrow(() -> new RuntimeException("Assignment attempt not found"));

        if (!assignmentAttempt.getStudent().getId().equals(input.getRequesterId())) {
            throw new RuntimeException("You are not allowed to update this assignment attempt");
        }

        AssignmentAttempt updatedAssignmentAttempt = update(input, assignmentAttempt);
        return new OutputValues(assignmentAttemptRepository.persist(updatedAssignmentAttempt));
    }

    public abstract AssignmentAttempt update(InputValues input, AssignmentAttempt assignmentAttempt);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentAttemptId;
        LocalDateTime submittedAt;
        String teacherComment;
        Integer totalMark;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        AssignmentAttempt assignmentAttempt;
    }
}
