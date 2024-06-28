package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.enums.AssignmentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="assignments")
@Getter
@Setter
@Table(name = "assignments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"lesson_id", "course_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AssignmentData{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    @Column(name="total_marks")
    private Integer totalMarks;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionData> questions;

    @Column(name="created_by")
    private UUID createdBy;

    @Column(name="subject_id")
    private UUID subjectId;

    private Long duration;
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private AssignmentType assignmentType;
    @Column(name="max_attempt_times")
    private Long maxAttemptTimes;

    @Column(name="lesson_id")
    private UUID lessonId;

    @Column(name="course_id")
    private UUID courseId;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.EAGER)
    private List<AssignmentAttemptData> attempts;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    public static AssignmentData from(Assignment assignment) {
        AssignmentData assignmentData = AssignmentData.builder()
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .totalMarks(assignment.getTotalMark())
                .createdBy(Objects.nonNull(assignment.getCreatedBy()) ? assignment.getCreatedBy().getUUID() : null)
                .subjectId(Objects.nonNull(assignment.getSubjectId()) ? assignment.getSubjectId().getUUID() : null)
                .duration(assignment.getDuration())
                .startTime(assignment.getStartTime())
                .endTime(assignment.getEndTime())
                .assignmentType(assignment.getAssignmentType())
                .maxAttemptTimes(assignment.getMaxAttemptTimes())
                .lessonId(Objects.nonNull(assignment.getLessonId()) ? assignment.getLessonId().getUUID() : null)
                .courseId(Objects.nonNull(assignment.getCourseId()) ? assignment.getCourseId().getUUID() : null)
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
                .createdBy(Objects.nonNull(createdBy) ? Identity.from(createdBy) : null)
                .subjectId(Objects.nonNull(subjectId) ? Identity.from(subjectId) : null)
                .questions(Objects.nonNull(questions)
                        ? questions.stream().map(QuestionData::fromThis).toList()
                        : null)
                .duration(duration)
                .startTime(startTime)
                .endTime(endTime)
                .lessonId(Objects.nonNull(lessonId) ? Identity.from(lessonId) : null)
                .courseId(Objects.nonNull(courseId) ? Identity.from(courseId) : null)
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
}
