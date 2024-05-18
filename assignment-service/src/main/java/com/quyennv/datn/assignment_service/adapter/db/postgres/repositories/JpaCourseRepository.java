package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCourseRepository extends JpaRepository<CourseData, UUID> {
    Optional<CourseData> findByCode(String code);
}
