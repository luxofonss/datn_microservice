package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.usecases.question.QuestionRepository;
import com.quyennv.lms.adapter.jpa.entities.QuestionData;
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
