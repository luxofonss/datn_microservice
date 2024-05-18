package com.quyennv.datn.courseservice.core.repositories;

import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;

public interface LessonStudentRepository {
    LessonStudent save(LessonStudent lessonStudent);
}
