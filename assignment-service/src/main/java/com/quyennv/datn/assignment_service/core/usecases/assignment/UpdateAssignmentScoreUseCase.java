package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswer;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionChoice;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionType;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateAssignmentScoreUseCase extends UseCase<UpdateAssignmentScoreUseCase.InputValues,
        UpdateAssignmentScoreUseCase.OutputValues> {
    private final AssignmentAttemptRepository assignmentAttemptRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public UpdateAssignmentScoreUseCase(AssignmentAttemptRepository assignmentAttemptRepository,
                                        QuestionAnswerRepository questionAnswerRepository) {
        this.assignmentAttemptRepository = assignmentAttemptRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        AssignmentAttempt assignmentAttempt = assignmentAttemptRepository.findById(input.getAttemptId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );

        AtomicReference<Integer> score = new AtomicReference<>(0);
        assignmentAttempt.getAssignment().getQuestions().forEach(q -> {
            QuestionAnswer qa =  questionAnswerRepository.findByAssignmentAttemptAndQuestion(
                        input.getAttemptId().getId(),
                        q.getId().getId()
                ).orElse(null);

            if (Objects.nonNull(qa)) {
                if (q.getType().equals(QuestionType.SINGLE_CHOICE)) {
                    for (QuestionChoice qc : q.getChoices()) {
                        if (qc.getIsCorrect().equals(Boolean.TRUE)) {
                            if (qa.getSelectedOptions().stream().anyMatch(selectedOption -> selectedOption.getId().equals(qc.getId()))) {
                                score.updateAndGet(v -> v + q.getMark());
                                break;
                            }
                        }
                    }
                } else if (q.getType().equals(QuestionType.MULTIPLE_CHOICES)) {
                    boolean isCorrect = true;

                    for (QuestionChoice qc : q.getChoices()) {
                        if (qc.getIsCorrect().equals(Boolean.TRUE)) {
                            if (qa.getSelectedOptions().stream().noneMatch(selectedOption -> selectedOption.getId().equals(qc.getId()))) {
                                isCorrect = false;
                                break;
                            }
                        }
                    }

                    if (isCorrect) {
                        score.updateAndGet(v -> v + q.getMark());
                    }
                } else if (q.getType().equals(QuestionType.SHORT_ANSWER) && q.getAnswer() != null) {
                    if (qa.getTextAnswer().toLowerCase().trim().equals(q.getAnswer().getTextAnswer().toLowerCase().trim())) {
                        score.updateAndGet(v -> v + q.getMark());
                    }
                } else if (q.getType().equals(QuestionType.LONG_ANSWER) && q.getAnswer() != null) {
                    if (q.getAnswer().getScore() != null) {
                        score.updateAndGet(v -> v + q.getAnswer().getScore());
                    }
                }
            }
        });
        assignmentAttempt.setTotalMark(score.get());

        return new OutputValues(assignmentAttemptRepository.persist(assignmentAttempt));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity attemptId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        AssignmentAttempt assignmentAttempt;
    }
}
