package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;
import com.quyennv.datn.courseservice.core.domain.enums.LessonStudentStatus;
import com.quyennv.datn.courseservice.core.domain.valueobject.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="lesson_students")
@Setter
@Getter
@Table(name="lesson_students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"lesson"})
public class LessonStudentData {
    @EmbeddedId
    LessonStudentDataKey id;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name="lesson_id")
    LessonData lesson;

    @Enumerated(EnumType.STRING)
    private LessonStudentStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static LessonStudentData from(LessonStudent ls) {
        return LessonStudentData
                .builder()
                .id(LessonStudentDataKey
                        .builder()
                        .lessonId(ls.getLessonId() != null ? ls.getLessonId().getId() : null)
                        .studentId(ls.getStudentId() != null ? ls.getStudentId().getId() : null)
                        .build())
                .lesson(ls.getLesson() != null ? LessonData.from(ls.getLesson()) : null)
                .status(ls.getStatus())
                .createdAt(ls.getCreatedAt())
                .updatedAt(ls.getUpdatedAt())
                .deletedAt(ls.getDeletedAt())
                .build();
    }

    public LessonStudent fromThis() {
        if (this.lesson != null) {
            this.lesson.setLessonStudents(null);
        }

        return LessonStudent
                .builder()
                .lesson(this.lesson != null ? this.lesson.fromThis() : null)
                .student(this.getId().getStudentId() != null ? User.builder().id(Identity.from(this.getId().getStudentId())).build() : null)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}
