package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentData, UUID> {

}