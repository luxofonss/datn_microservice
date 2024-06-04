package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.AssignmentAttempt;

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
