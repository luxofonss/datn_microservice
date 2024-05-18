package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseStudentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCourseStudentRepository extends JpaRepository<CourseStudentData, UUID> {
    Optional<CourseStudentData> findByCourseIdAndStudentId(UUID courseId, UUID studentId);
    List<CourseStudentData> findByCourseId(UUID courseId);
}
