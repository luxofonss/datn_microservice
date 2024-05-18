package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonStudentDataKey implements Serializable {
    @Column(name="lesson_id")
    UUID lessonId;
    @Column(name="student_id")
    UUID studentId;
}
