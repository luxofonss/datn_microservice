package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="course_students")
@Setter
@Getter
@Table(name="course_students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"course", "student"})
@Slf4j
public class CourseStudentData {
    @EmbeddedId
    CourseStudentDataKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    CourseData course;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    UserData student;

    @Enumerated(EnumType.STRING)
    private EnrollStatus status;
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static CourseStudentData from(CourseStudent cs) {
        log.info("cs:: {}", cs);
        return CourseStudentData
                .builder()
                .id(CourseStudentDataKey
                        .builder()
                        .courseId(cs.getCourse().getId().getId())
                        .studentId(cs.getStudent().getId().getUUID())
                        .build())
                .course(CourseData.newWithId(cs.getCourse().getId()))
//                .studentId(cs.getStudentId().getUUID())
                .student(UserData.from(cs.getStudent()))
                .course(CourseData.from(cs.getCourse()))
                .price(cs.getPrice())
                .status(cs.getStatus())
                .createdAt(cs.getCreatedAt())
                .updatedAt(cs.getUpdatedAt())
                .deletedAt(cs.getDeletedAt())
                .build();
    }

    public CourseStudent fromThis() {
        if (this.course != null) {
            this.course.setCourseStudents(null);
        }

        return CourseStudent
                .builder()
//                .studentId(this.getStudentId() != null ? Identity.from(this.getStudentId()) : null)
                .student(this.student.fromThis())
                .course(this.course != null ? this.course.getInfo() : null)
                .price(this.price)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}
