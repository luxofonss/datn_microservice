package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.core.domain.enums.LessonStudentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name="lesson_students")
@Setter
@Getter
@Table(name="lesson_students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"lesson", "student"})
@Slf4j
public class LessonStudentData {
    @EmbeddedId
    LessonStudentDataKey id;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name="lesson_id")
    LessonData lesson;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    UserData student;

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
        LessonStudentData lesson = LessonStudentData
                .builder()
                .id(LessonStudentDataKey
                        .builder()
                        .lessonId(ls.getLessonId() != null ? ls.getLessonId().getId() : null)
                        .studentId(ls.getStudentId() != null ? ls.getStudentId().getId() : null)
                        .build())
                .lesson(ls.getLesson() != null ? LessonData.from(ls.getLesson()) : null)
                .student(ls.getStudent() != null ? UserData.from(ls.getStudent()) : null)
                .status(ls.getStatus())
                .createdAt(ls.getCreatedAt())
                .updatedAt(ls.getUpdatedAt())
                .deletedAt(ls.getDeletedAt())
                .build();
        log.info("LessonStudentData.from: {}", lesson);
        return lesson;
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
