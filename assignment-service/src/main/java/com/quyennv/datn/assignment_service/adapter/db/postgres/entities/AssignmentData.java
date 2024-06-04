package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Lesson;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name="assignments")
@Getter
@Setter
@Table(name="assignments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AssignmentData extends BaseEntity{
    private String title;
    private String description;
    @Column(name="total_marks")
    private Integer totalMarks;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionData> questions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", nullable = false)
    private UserData creator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="subject_id", nullable = false)
    private SubjectData subject;

    private Long duration;
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private AssignmentType assignmentType;
    @Column(name="max_attempt_times")
    private Long maxAttemptTimes;
    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="lesson_id", nullable = true)
    private LessonData lesson;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.EAGER)
    private List<AssignmentAttemptData> attempts;

    public static AssignmentData from(Assignment assignment) {
        AssignmentData assignmentData = AssignmentData.builder()
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .totalMarks(assignment.getTotalMark())
                .creator(Objects.nonNull(assignment.getCreator()) ? UserData.from(assignment.getCreator()) : null)
                .subject(Objects.nonNull(assignment.getSubject()) ? SubjectData.from(assignment.getSubject()) : null)
                .duration(assignment.getDuration())
                .startTime(assignment.getStartTime())
                .endTime(assignment.getEndTime())
                .assignmentType(assignment.getAssignmentType())
                .maxAttemptTimes(assignment.getMaxAttemptTimes())
                .lesson(Objects.nonNull(assignment.getLesson()) ? LessonData.from(assignment.getLesson()) : null)
                .attempts(
                        Objects.nonNull(assignment.getAttempts())
                                ? assignment.getAttempts().stream().map(AssignmentAttemptData::from).toList()
                                : null)
                .build();

        if (Objects.nonNull(assignment.getId())) {
            assignmentData.setId(assignment.getId().getId());
        }

        if (Objects.nonNull(assignment.getQuestions())) {
            List<QuestionData> questions = assignment.getQuestions().stream().map(
                    q -> {
                        QuestionData question = QuestionData.from(q);
                        question.setAssignment(assignmentData);
                        return question;
                    }
            ).toList();
            assignmentData.setQuestions(questions);
        }


        assignmentData.setCreatedAt(assignment.getCreatedAt());
        assignmentData.setUpdatedAt(assignment.getUpdatedAt());
        assignmentData.setDeletedAt(assignment.getDeletedAt());

        return assignmentData;
    }

    public Assignment fromThis() {
        return Assignment
                .builder()
                .id(Objects.nonNull(this.getId()) ? Identity.from(this.getId()) : null)
                .title(title)
                .description(description)
                .totalMark(totalMarks)
                .creator(Objects.nonNull(creator) ? creator.fromThis() : null)
                .subject(Objects.nonNull(subject) ? subject.fromThis() : null)
                .questions(Objects.nonNull(questions)
                        ? questions.stream().map(QuestionData::fromThis).toList()
                        : null)
                .duration(duration)
                .startTime(startTime)
                .endTime(endTime)
//                .lesson(Objects.nonNull(lesson) ? lesson.fromThis() : null)
                .assignmentType(assignmentType)
                .maxAttemptTimes(maxAttemptTimes)
                .attempts(Objects.nonNull(attempts)
                        ? attempts.stream().map(AssignmentAttemptData::fromResult).toList()
                        : null)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }

    public Assignment info() {
        return Assignment
                .builder()
                .id(Objects.nonNull(this.getId()) ? Identity.from(this.getId()) : null)
                .title(title)
                .description(description)
                .totalMark(totalMarks)
                .duration(duration)
                .startTime(startTime)
                .endTime(endTime)
//                .lesson(Objects.nonNull(lesson) ? lesson.fromThis() : null)
                .assignmentType(assignmentType)
                .maxAttemptTimes(maxAttemptTimes)
                .attempts(Objects.nonNull(attempts)
                        ? attempts.stream().map(AssignmentAttemptData::fromThis).toList()
                        : null)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }
}
