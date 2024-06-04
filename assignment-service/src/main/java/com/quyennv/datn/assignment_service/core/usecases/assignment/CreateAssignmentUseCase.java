package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.*;
import com.quyennv.lms.core.domain.enums.*;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class CreateAssignmentUseCase extends UseCase<
        CreateAssignmentUseCase.InputValues, CreateAssignmentUseCase.OutputValues> {
    private final AssignmentRepository assignmentRepository;

    public CreateAssignmentUseCase(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Assignment assignment = map(input);
        return new OutputValues(assignmentRepository.persist(assignment));
    }

    private Assignment map(InputValues input) {
        AtomicReference<Integer> mark = new AtomicReference<>(0);
        if (Objects.nonNull(input.getQuestions()) && !input.getQuestions().isEmpty()) {
            input.getQuestions().forEach(
                    q-> mark.updateAndGet(v -> v + q.getMark())
            );
        } else {
            throw new RuntimeException("Assignment must have question");
        }

        Assignment assignment = Assignment
                .builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .creator(User.builder().id(input.getTeacherId()).build())
                .subject(Subject.builder().id(input.getSubjectId()).build())
                .totalMark(mark.get())
                .duration(input.getDuration())
                .startTime(input.getStartTime())
                .endTime(input.getEndTime())
                .assignmentType(input.getAssignmentType())
                .maxAttemptTimes(input.getMaxAttemptTimes())
                .lesson(Objects.nonNull(input.getLessonId()) ? Lesson.builder().id(input.getLessonId()).build() : null)
                .build();

        List<Question> questions = mapQuestions(input.getQuestions(), input);
        assignment.setQuestions(questions);

        return assignment;
    }

    private List<Question> mapQuestions(List<QuestionInput> questions, InputValues input) {
        return questions.stream().map(
                q -> {
                    Question question = Question
                            .builder()
                            .level(q.getLevel())
                            .title(q.getTitle())
                            .image(q.getImage())
                            .audio(q.getAudio())
                            .subject(Subject.builder().id(input.getSubjectId()).build())
                            .mark(q.getMark())
                            .type(q.getType())
                            .answerExplanation(q.getAnswerExplanation())
                            .creator(User.builder().id(input.getTeacherId()).build())
                            .build();

                    if(Objects.nonNull(q.getChoices())) {
                        question.setChoices(q.getChoices().stream().map(
                                c -> QuestionChoice
                                        .builder()
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
                                        .answer(ta.getAnswer())
                                        .explanation(ta.getExplanation())
                                        .build()
                        ).toList());
                    }

                    if (Objects.nonNull(q.getSubQuestions())) {
                        question.setSubQuestions(mapQuestions(q.getSubQuestions(), input));
                    }

                    return question;
                }
        ).toList();
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
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
        Identity lessonId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Assignment assignment;
    }

    @Value
    @Builder
    public static class QuestionInput {
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
        String content;
        Integer order;
        Boolean isCorrect;
        String explanation;
    }

    @Value
    @Builder
    public static class QuestionTextAnswerInput {
        String answer;
        String explanation;
    }

    @Value
    @Builder
    public static class AssignmentPlacementInput {
        Identity id;
        String name;
        AssignmentPlacementType type;
        AttemptType attemptType;
        Long duration;
        LocalDateTime startTime;
        LocalDateTime endTime;
        AssignmentType assignmentType;
    }
}
