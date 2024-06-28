package com.quyennv.datn.assignment_service.core.usecases.assignment_attempt;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Value;

public class GetAssignmentAttemptByIdUseCase extends UseCase<
        GetAssignmentAttemptByIdUseCase.InputValues, GetAssignmentAttemptByIdUseCase.OutputValues>{

    private final AssignmentAttemptRepository assignmentAttemptRepository;

    public GetAssignmentAttemptByIdUseCase(AssignmentAttemptRepository assignmentAttemptRepository) {
        this.assignmentAttemptRepository = assignmentAttemptRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
          AssignmentAttempt attempt = assignmentAttemptRepository
                        .findById(input.getId())
                        .orElseThrow(() -> new RuntimeException("Assignment attempt not found."));

        for (Question question : attempt.getAssignment().getQuestions()) {
            question.setAnswer(
                    attempt.getAnswers().stream()
                            .filter(qa ->
                                    qa.getQuestion().getId().equals(question.getId())
                                            && qa.getCreatorId().equals(attempt.getStudent().getId()))
                            .findFirst().orElse(null)
            );
        }

        return new OutputValues(attempt);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        AssignmentAttempt attempt;
    }
}
