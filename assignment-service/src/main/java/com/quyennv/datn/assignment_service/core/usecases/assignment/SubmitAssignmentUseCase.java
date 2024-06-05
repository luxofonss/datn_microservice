package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.UpdateAssignmentAttemptUseCase;

import java.time.LocalDateTime;

public class SubmitAssignmentUseCase extends UpdateAssignmentAttemptUseCase {
    private final UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase;
    public SubmitAssignmentUseCase(AssignmentAttemptRepository assignmentAttemptRepository, UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        super(assignmentAttemptRepository);
        this.updateAssignmentScoreUseCase = updateAssignmentScoreUseCase;
    }

    @Override
    public AssignmentAttempt update(InputValues input, AssignmentAttempt assignmentAttempt) {
        if (assignmentAttempt.getEndTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Assignment has ended");
        }

        updateAssignmentScoreUseCase.execute(UpdateAssignmentScoreUseCase.InputValues.builder()
                .attemptId(assignmentAttempt.getId())
                .build());

        return assignmentAttempt.submit();
    }
}
