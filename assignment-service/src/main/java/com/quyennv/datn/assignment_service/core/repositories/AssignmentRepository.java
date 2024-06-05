package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {
    Assignment persist(Assignment assignment);
    Optional<Assignment> findById(Identity id);
    List<Assignment> findAllWithFilter(
            String title,
            Identity teacherId,
            Identity subjectId,
            Identity courseId,
            List<Identity> sectionIds,
            Identity studentId
    );
}
