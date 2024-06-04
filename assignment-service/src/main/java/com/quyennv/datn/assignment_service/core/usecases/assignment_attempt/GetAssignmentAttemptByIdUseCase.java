package com.quyennv.lms.core.usecases.assignment;

import com.amazonaws.services.kms.model.NotFoundException;
import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.usecases.UseCase;
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
                        .orElseThrow(() -> new NotFoundException("Assignment attempt not found."));

        for (Question question : attempt.getAssignment().getQuestions()) {
            question.setAnswer(
                    attempt.getAnswers().stream()
                            .filter(qa ->
                                    qa.getQuestion().getId().equals(question.getId())
                                            && qa.getCreator().getId().equals(attempt.getStudentId()))
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
