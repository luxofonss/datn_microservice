package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.*;
import com.quyennv.datn.assignment_service.core.domain.enums.AssignmentType;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionLevel;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionType;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class UpdateAssignmentUseCase extends UseCase<
        UpdateAssignmentUseCase.InputValues, UpdateAssignmentUseCase.OutputValues>{
    private final AssignmentRepository assignmentRepository;

    protected UpdateAssignmentUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Assignment assignment = assignmentRepository.findById(input.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        if (checkPermission(input, assignment).equals(Boolean.FALSE)) {
            throw new RuntimeException("Error permission");
        }

        Assignment updatedAssignment = update(input, assignment);
        return new OutputValues(assignmentRepository.persist(updatedAssignment));
    }

    public abstract Assignment update(InputValues input, Assignment assignment);

    public List<Question> mapQuestions(List<QuestionInput> questions, InputValues input) {
        return questions.stream().map(
                q -> {
                    Question question = Question
                            .builder()
                            .id(q.getId())
                            .level(q.getLevel())
                            .title(q.getTitle())
                            .image(q.getImage())
                            .audio(q.getAudio())
                            .subjectId(input.getSubjectId())
                            .mark(q.getMark())
                            .type(q.getType())
                            .answerExplanation(q.getAnswerExplanation())
                            .creatorId(input.getTeacherId())
                            .build();

                    if(Objects.nonNull(q.getChoices())) {
                        question.setChoices(q.getChoices().stream().map(
                                c -> QuestionChoice
                                        .builder()
                                        .id(c.getId())
                                        .content(c.getContent())
                                        .order(c.getOrder())
                                        .isCorrect(c.getIsCorrect())
                                        .explanation(c.getExplanation())
                                        .build()
                        ).toList());
                    }
                    if(Objects.nonNull(q.getTextAnswers())) {
                        question.setTextAnswers(q.getTextAnswers().stream().map(
                                ta -> QuestionTextAnswer
                                        .builder()
                                        .id(ta.getId())
                                        .answer(ta.getAnswer())
                                        .explanation(ta.getExplanation())
                                        .build()
                        ).toList());
                    }

                    if(Objects.nonNull(q.getSubQuestions())) {
                        question.setSubQuestions(mapQuestions(q.getSubQuestions(), input));
                    }

                    return question;
                }
        ).toList();
    }

    private Boolean checkPermission(InputValues input, Assignment assignment) {
        return true;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity assignmentId;
        String title;
        String description;
        Identity teacherId;
        Identity subjectId;
        List<QuestionInput> questions;
        Long duration;
        LocalDateTime startTime;
        LocalDateTime endTime;
        AssignmentType assignmentType;
        Long maxAttemptTimes;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Assignment assignment;
    }

    @Data
    @Builder
    public static class QuestionInput {
        Identity id;
        String title;
        String description;
        String image;
        String audio;
        Integer mark;
        QuestionLevel level;
        QuestionType type;
        String answerExplanation;
        List<QuestionChoiceInput> choices;
        List<QuestionTextAnswerInput> textAnswers;
        List<QuestionInput> subQuestions;
    }

    @Value
    @Builder
    public static class QuestionChoiceInput {
        Identity id;
        String content;
        Integer order;
        Boolean isCorrect;
        String explanation;
    }

    @Value
    @Builder
    public static class QuestionTextAnswerInput {
        Identity id;
        String answer;
        String explanation;
    }
}
