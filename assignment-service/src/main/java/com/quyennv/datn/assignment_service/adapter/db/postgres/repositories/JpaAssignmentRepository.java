package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentData, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM assignments a WHERE a.lessonId = :lessonId AND a.courseId = :courseId")
    void deleteByLessonIdAndCourseId(@Param("lessonId") UUID lessonId, @Param("courseId") UUID courseId);


    @Transactional
    @Query(value = "SELECT a.* FROM assignments a INNER JOIN assignment_attempts aa ON a.id = aa.assignment_id WHERE aa.id = :assignmentAttemptId LIMIT 1", nativeQuery = true)
    Optional<AssignmentData> findByAssignmentAttemptId(@Param("assignmentAttemptId") UUID assignmentAttemptId);
}