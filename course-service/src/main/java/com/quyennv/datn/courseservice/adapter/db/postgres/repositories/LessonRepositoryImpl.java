package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.LessonData;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepository {
    private final JpaLessonRepository jpaLessonRepository;
    @Override
    public Optional<Lesson> findById(Identity id) {
        return jpaLessonRepository.findById(id.getUUID()).map(LessonData::fromThis);
    }

    @Override
    public Lesson persist(Lesson lesson) {
        return jpaLessonRepository.save(LessonData.from(lesson)).fromThis();
    }
}
