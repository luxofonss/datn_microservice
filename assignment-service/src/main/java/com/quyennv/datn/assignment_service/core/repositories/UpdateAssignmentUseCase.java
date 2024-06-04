package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.*;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.domain.enums.QuestionLevel;
import com.quyennv.lms.core.domain.enums.QuestionType;
import com.quyennv.lms.core.usecases.UseCase;
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

    public List<Question> mapQuestions(List<UpdateAssignmentUseCase.QuestionInput> questions, InputValues input) {
        return questions.stream().map(
                q -> {
                    Question question = Question
                            .builder()
                            .id(q.getId())
                            .level(q.getLevel())
                            .title(q.getTitle())
                            .image(q.getImage())
                            .audio(q.getAudio())
                            .subject(Objects.nonNull(input.getSubjectId()) ? Subject.builder().id(input.getSubjectId()).build() : null)
                            .mark(q.getMark())
                            .type(q.getType())
                            .answerExplanation(q.getAnswerExplanation())
                            .creator(User.builder().id(input.getTeacherId()).build())
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
        List<UpdateAssignmentUseCase.QuestionInput> questions;
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
        List<UpdateAssignmentUseCase.QuestionChoiceInput> choices;
        List<UpdateAssignmentUseCase.QuestionTextAnswerInput> textAnswers;
        List<UpdateAssignmentUseCase.QuestionInput> subQuestions;
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
