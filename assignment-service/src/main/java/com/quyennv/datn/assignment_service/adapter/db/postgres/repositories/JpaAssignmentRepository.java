package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.AssignmentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentData, UUID> {

}