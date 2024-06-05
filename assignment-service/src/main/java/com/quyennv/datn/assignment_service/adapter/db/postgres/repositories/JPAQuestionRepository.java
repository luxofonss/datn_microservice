package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;


import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.QuestionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAQuestionRepository extends JpaRepository<QuestionData, UUID> {
}
