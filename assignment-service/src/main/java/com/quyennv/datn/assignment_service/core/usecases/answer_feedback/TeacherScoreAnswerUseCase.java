package com.quyennv.datn.assignment_service.core.usecases.answer_feedback;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswer;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment.UpdateAssignmentScoreUseCase;
import lombok.Builder;
import lombok.Value;

public class TeacherScoreAnswerUseCase extends UseCase<TeacherScoreAnswerUseCase.InputValues, TeacherScoreAnswerUseCase.OutputValues> {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase;

    public TeacherScoreAnswerUseCase(QuestionAnswerRepository questionAnswerRepository, UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.updateAssignmentScoreUseCase = updateAssignmentScoreUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        QuestionAnswer questionAnswer = questionAnswerRepository.findByAssignmentAttemptAndQuestion(input.attemptId.getId(), input.questionId.getId())
                .orElseThrow(() -> new RuntimeException("QuestionAnswer not found"));

        questionAnswer.setScore(input.score);

        QuestionAnswer updatedQuestionAnswer = questionAnswerRepository.persist(questionAnswer);
        updateAssignmentScoreUseCase.execute(UpdateAssignmentScoreUseCase.InputValues.builder()
                .attemptId(input.getAttemptId())
                .build());

        return new OutputValues(updatedQuestionAnswer);
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity attemptId;
        Identity questionId;
        Integer score;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        QuestionAnswer questionAnswer;
    }
}
