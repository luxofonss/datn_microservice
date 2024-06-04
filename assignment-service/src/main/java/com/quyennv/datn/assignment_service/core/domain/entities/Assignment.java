package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.utils.FunctionHelper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
@ToString
public class Assignment {
    private Identity id;
    private String title;
    private String description;
    private Integer totalMark;
    private Subject subject;
    private List<Question> questions;

    private Long duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AssignmentType assignmentType;
    private Long maxAttemptTimes;
    private Lesson lesson;
    private User creator;

    private List<AssignmentAttempt> attempts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Assignment update(Assignment updateAssignment) {
        BeanUtils.copyProperties(updateAssignment, this, FunctionHelper.getNullPropertyNames(updateAssignment));

        if (Objects.nonNull(updateAssignment.getQuestions())) {
            this.questions.forEach(q -> {
                updateAssignment.getQuestions().forEach(updatedQuestion -> {
                    if (q.getId().equals(updatedQuestion.getId())) {
                        q.update(updatedQuestion);
                    }
                });
            });
            this.setQuestions(updateAssignment.getQuestions());
        }

        AtomicReference<Integer> newTotalMark = new AtomicReference<>(0);

        this.getQuestions().forEach(
                q -> newTotalMark.updateAndGet(v -> v + updateAssignment.getTotalMark())
        );
        this.setTotalMark(newTotalMark.get());

        return this;
    }

    public Assignment addQuestions(List<Question> questions) {
        if (Objects.isNull(questions) || questions.isEmpty()) {
            throw new RuntimeException("Questions are required");
        }
        List<Question> newQuestions = new ArrayList<>(this.getQuestions());
        newQuestions.addAll(questions);

        this.setQuestions(newQuestions);

        AtomicReference<Integer> newTotalMark = new AtomicReference<>(0);
        this.getQuestions().forEach(
                q -> newTotalMark.updateAndGet(v -> v + q.getMark())
        );
        this.setTotalMark(newTotalMark.get());
        return this;
    }
}
