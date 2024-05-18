package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseStudentDataKey;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.domain.valueobject.User;
import jakarta.persistence.*;
import lombok.*;
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
@ToString(exclude = {"course"})
@IdClass(CourseStudentDataKey.class)
public class CourseStudentData {
    @Id
    @Column(name="student_id")
    private UUID studentId;

    @Id
    @Column(name="course_id")
    private UUID courseId;


    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    CourseData course;
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
        return CourseStudentData
                .builder()
                .courseId(cs.getCourse().getId().getUUID())
                .studentId(cs.getStudentId().getUUID())
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
                .studentId(this.getStudentId() != null ? Identity.from(this.getStudentId()) : null)
                .course(this.course != null ? this.course.getInfo() : null)
                .price(this.price)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}
