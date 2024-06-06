package com.quyennv.datn.courseservice.core.repositories;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;

import java.util.Optional;

public interface LessonRepository {
    Optional<Lesson> findById(Identity id);
    Lesson persist(Lesson lesson);
}
