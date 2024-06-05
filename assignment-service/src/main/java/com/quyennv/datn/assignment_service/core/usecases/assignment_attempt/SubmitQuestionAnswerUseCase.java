package com.quyennv.datn.assignment_service.core.usecases.assignment_attempt;

import com.quyennv.datn.assignment_service.core.domain.entities.*;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment.UpdateAssignmentScoreUseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class SubmitQuestionAnswerUseCase extends UseCase<
        SubmitQuestionAnswerUseCase.InputValues, SubmitQuestionAnswerUseCase.OutputValues>{

    private final AssignmentAttemptRepository assignmentAttemptRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase;

    public SubmitQuestionAnswerUseCase(AssignmentAttemptRepository assignmentAttemptRepository,
                                       QuestionAnswerRepository questionAnswerRepository,
                                       UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        this.assignmentAttemptRepository = assignmentAttemptRepository;
        this.questionAnswerRepository = questionAnswerRepository;
        this.updateAssignmentScoreUseCase = updateAssignmentScoreUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        AssignmentAttempt assignmentAttempt = assignmentAttemptRepository.findById(input.getAssignmentAttemptId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        // check if user has submitted assignment
        if (Objects.nonNull(assignmentAttempt.getSubmittedAt())){
            throw new RuntimeException("You have already submitted the assignment");
        }

        // check if submission is still open
        if (assignmentAttempt.getEndTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Submission is closed");
        }

        // check if question is in the assignment
        if (assignmentAttempt.getAssignment().getQuestions().stream().noneMatch(q -> q.getId().equals(input.getQuestionId()))) {
            throw new RuntimeException("Question not found in assignment");
        }

        // check if question is already answered
        QuestionAnswer questionAnswer = questionAnswerRepository.findByAssignmentAttemptAndQuestion(
                input.getAssignmentAttemptId().getId(),
                input.getQuestionId().getId()
        ).orElse(null);

        QuestionAnswer newAnswer = QuestionAnswer
                .builder()
                .question(Question.builder().id(input.getQuestionId()).build())
                .textAnswer(input.getTextAnswer())
                .creatorId(input.requesterId)
                .selectedOptions(Objects.nonNull(input.getSelectedAnswerIds()) ? input.getSelectedAnswerIds().stream().map(optionId ->
                        QuestionChoice.builder().id(optionId).build()).collect(Collectors.toSet()) : null)
                .assignmentAttempt(assignmentAttempt)
                .build();
//
//        AtomicReference<Integer> score = new AtomicReference<>(0);
//        assignmentAttempt.getAssignment().getQuestions().forEach(q -> {
//            QuestionAnswer qa = null;
//            if (q.getId().equals(newAnswer.getQuestion().getId())) {
//                qa = newAnswer;
//            } else {
//                qa = questionAnswerRepository.findByAssignmentAttemptAndQuestion(
//                        input.getAssignmentAttemptId().getId(),
//                        q.getId().getId()
//                ).orElse(null);
//            }
//
//            if (Objects.nonNull(qa)) {
//                if (q.getType().equals(QuestionType.SINGLE_CHOICE)) {
//                    for (QuestionChoice qc : q.getChoices()) {
//                        if (qc.getIsCorrect().equals(Boolean.TRUE)) {
//                            if (qa.getSelectedOptions().stream().anyMatch(selectedOption -> selectedOption.getId().equals(qc.getId()))) {
//                                score.updateAndGet(v -> v + q.getMark());
//                                break;
//                            }
//                        }
//                    }
//                } else if (q.getType().equals(QuestionType.MULTIPLE_CHOICES)) {
//                    boolean isCorrect = true;
//
//                    for (QuestionChoice qc : q.getChoices()) {
//                        if (qc.getIsCorrect().equals(Boolean.TRUE)) {
//                            if (qa.getSelectedOptions().stream().noneMatch(selectedOption -> selectedOption.getId().equals(qc.getId()))) {
//                                isCorrect = false;
//                                break;
//                            }
//                        }
//                    }
//
//                    if (isCorrect) {
//                        score.updateAndGet(v -> v + q.getMark());
//                    }
//                } else if (q.getType().equals(QuestionType.SHORT_ANSWER) && q.getAnswer() != null) {
//                    if (qa.getTextAnswer().toLowerCase().trim().equals(q.getAnswer().getTextAnswer().toLowerCase().trim())) {
//                        score.updateAndGet(v -> v + q.getMark());
//                    }
//                }
//            }
//        });
//        assignmentAttempt.setTotalMark(score.get());
//        assignmentAttemptRepository.persist(assignmentAttempt);

        updateAssignmentScoreUseCase.execute(UpdateAssignmentScoreUseCase.InputValues.builder()
                .attemptId(assignmentAttempt.getId())
                .build());

        if (Objects.nonNull(questionAnswer)) {
            // delete previous answer if exists
            questionAnswerRepository.delete(questionAnswer);
        }

        return new OutputValues(questionAnswerRepository.persist(newAnswer));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentAttemptId;
        Identity questionId;
        List<Identity> selectedAnswerIds;
        String textAnswer;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        QuestionAnswer questionAnswer;
    }
}
