package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;


import com.quyennv.datn.courseservice.adapter.db.postgres.entities.LessonStudentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLessonStudentRepository extends JpaRepository<LessonStudentData, UUID> {
}
