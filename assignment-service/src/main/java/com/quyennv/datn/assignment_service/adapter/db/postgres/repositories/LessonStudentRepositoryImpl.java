package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.LessonStudentData;
import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;
import com.quyennv.datn.courseservice.core.repositories.LessonStudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LessonStudentRepositoryImpl implements LessonStudentRepository {
    private final JpaLessonStudentRepository jpaLessonStudentRepository;

    public LessonStudentRepositoryImpl(JpaLessonStudentRepository jpaLessonStudentRepository) {
        this.jpaLessonStudentRepository = jpaLessonStudentRepository;
    }

    @Override
    @Transactional
    public LessonStudent save(LessonStudent lessonStudent) {
        return jpaLessonStudentRepository.save(LessonStudentData.from(lessonStudent)).fromThis();
    }
}
