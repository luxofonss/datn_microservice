package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name="assignment_attempts")
@Getter
@Setter
@Table(name="assignment_attempts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AssignmentAttemptData extends BaseEntity{
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Column(name="teacher_comment")
    private String teacherComment;
    @Column(name="total_marks")
    private Integer totalMark;
    @Column(name="submitted_at")
    private LocalDateTime submittedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignmentAttempt")
    private List<QuestionAnswerData> answers;
    @ManyToOne
    @JoinColumn(name="assignment__id")
    private AssignmentData assignment;
    @ManyToOne
    @JoinColumn(name="student_id")
    private UserData student;

    public static AssignmentAttemptData from(AssignmentAttempt aa) {
        AssignmentAttemptData result = AssignmentAttemptData.builder()
                .startTime(aa.getStartTime())
                .endTime(aa.getEndTime())
                .teacherComment(aa.getTeacherComment())
                .totalMark(aa.getTotalMark())
                .assignment(Objects.nonNull(aa.getAssignment()) ?  AssignmentData.from(aa.getAssignment()) : null)
                .student(aa.getStudent() != null ? UserData.from(aa.getStudent()) : null)
                .submittedAt(aa.getSubmittedAt())
                .build();

        if (Objects.nonNull(aa.getAnswers())) {
            result.setAnswers(aa.getAnswers().stream().map(QuestionAnswerData::from).toList());
        }

        if (Objects.nonNull(aa.getId())) {
            result.setId(aa.getId().getId());
        }

        result.setCreatedAt(aa.getCreatedAt());
        result.setUpdatedAt(aa.getUpdatedAt());
        result.setDeletedAt(aa.getDeletedAt());

        return result;
    }

    public AssignmentAttempt fromThis() {
        return AssignmentAttempt.builder()
                .id(Identity.from(this.getId()))
                .student(this.student.fromThis())
                .assignment(this.assignment.fromThis())
                .answers(Objects.nonNull(this.answers) ? this.answers.stream().map(QuestionAnswerData::fromThis).toList() : null)
                .startTime(this.getStartTime())
                .endTime(this.getEndTime())
                .totalMark(this.getTotalMark())
                .submittedAt(this.getSubmittedAt())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }

    public AssignmentAttempt fromResult() {
        return AssignmentAttempt.builder()
                .id(Identity.from(this.getId()))
                .student(this.student.fromThis())
                .startTime(this.getStartTime())
                .endTime(this.getEndTime())
                .totalMark(this.getTotalMark())
                .submittedAt(this.getSubmittedAt())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
