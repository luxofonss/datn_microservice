package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.*;
import com.quyennv.datn.assignment_service.core.domain.enums.*;
import com.quyennv.datn.assignment_service.core.domain.event.AssignmentCreatedEvent;
import com.quyennv.datn.assignment_service.core.usecases.EventPublisher;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
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
    private final EventPublisher eventPublisher;

    public CreateAssignmentUseCase(AssignmentRepository assignmentRepository,
                                   EventPublisher eventPublisher) {
        this.assignmentRepository = assignmentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Assignment assignment = map(input);
        assignment = assignmentRepository.persist(assignment);
        publishEvent(assignment);
        return new OutputValues(assignment);
    }

    private void publishEvent(Assignment assignment) {
        AssignmentCreatedEvent event = new AssignmentCreatedEvent(
                assignment.getId().getId().toString(),
                assignment.getLessonId().getId().toString(),
                assignment.getTitle()
        );
        eventPublisher.publish(event);
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
                .createdBy(input.getTeacherId())
                .subjectId(input.getSubjectId())
                .totalMark(mark.get())
                .duration(input.getDuration())
                .startTime(input.getStartTime())
                .endTime(input.getEndTime())
                .assignmentType(input.getAssignmentType())
                .maxAttemptTimes(input.getMaxAttemptTimes())
                .lessonId(input.getLessonId())
                .courseId(input.getCourseId())
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
                            .subjectId(input.getSubjectId())
                            .creatorId(input.getTeacherId())
                            .mark(q.getMark())
                            .type(q.getType())
                            .answerExplanation(q.getAnswerExplanation())
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
        Identity courseId;
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
