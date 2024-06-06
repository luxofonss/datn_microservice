package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.LessonData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLessonRepository extends JpaRepository<LessonData, UUID> {
}
