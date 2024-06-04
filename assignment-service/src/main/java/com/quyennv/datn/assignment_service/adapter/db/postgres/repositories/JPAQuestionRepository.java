package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.QuestionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAQuestionRepository extends JpaRepository<QuestionData, UUID> {
}
