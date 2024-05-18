package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;

public interface LessonStudentRepository {
    LessonStudent save(LessonStudent lessonStudent);
}
