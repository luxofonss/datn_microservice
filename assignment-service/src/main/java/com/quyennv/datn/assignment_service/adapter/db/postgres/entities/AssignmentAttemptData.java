package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.UserData;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="assignment_attempts")
@Getter
@Setter
@Table(name="assignment_attempts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AssignmentAttemptData{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    @JoinColumn(name="assignment_id")
    private AssignmentData assignment;
//
//    @Column(name="student_id")
//    private UUID studentId;

    @OneToOne
    @JoinColumn(name="student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserData student;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static AssignmentAttemptData from(AssignmentAttempt aa) {
        AssignmentAttemptData result = AssignmentAttemptData.builder()
                .startTime(aa.getStartTime())
                .endTime(aa.getEndTime())
                .teacherComment(aa.getTeacherComment())
                .totalMark(aa.getTotalMark())
//                .studentId(Objects.nonNull(aa.getStudentId()) ? aa.getStudentId().getUUID() : null)
                .student(Objects.nonNull(aa.getStudentId()) ? UserData.builder().id(aa.getStudentId().getId()).build() : null)
                .submittedAt(aa.getSubmittedAt())
                .assignment(Objects.nonNull(aa.getAssignment()) ?  AssignmentData.from(aa.getAssignment()) : null)
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
//                .studentId(Identity.from(this.studentId))
                .student(Objects.nonNull(this.student) ? this.student.fromThis() : null)
                .assignment(Objects.nonNull(assignment) ? this.assignment.fromThis() : null)
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
                .startTime(this.getStartTime())
                .student(Objects.nonNull(this.student) ? this.student.fromThis() : null)
                .endTime(this.getEndTime())
                .totalMark(this.getTotalMark())
                .submittedAt(this.getSubmittedAt())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
