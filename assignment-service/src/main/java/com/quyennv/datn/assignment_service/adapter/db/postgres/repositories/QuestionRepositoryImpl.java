package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.QuestionData;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;
import com.quyennv.datn.assignment_service.core.repositories.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private final JPAQuestionRepository jpaQuestionRepository;

    public QuestionRepositoryImpl(JPAQuestionRepository jpaQuestionRepository) {
        this.jpaQuestionRepository = jpaQuestionRepository;
    }

    @Override
    public List<Question> getByIds(List<Identity> ids) {
        return jpaQuestionRepository.findAllById(
                ids.stream()
                        .map(Identity::getId)
                        .toList())
                .stream()
                .map(QuestionData::fromThis)
                .toList();
    }
}
