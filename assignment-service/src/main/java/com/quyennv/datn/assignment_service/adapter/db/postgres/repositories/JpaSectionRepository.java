package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.courseservice.adapter.db.postgres.entities.SectionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaSectionRepository extends JpaRepository<SectionData, UUID> {
    List<SectionData> findByCourseId(UUID courseId);
}
