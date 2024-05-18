package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentDataKey implements Serializable {
    @Column(name="course_id")
    UUID courseId;

    @Column(name="student_id")
    UUID studentId;
}
