package com.quyennv.datn.assignment_service.core.usecases.lesson;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;

import java.util.List;

public class UpdateLessonsUseCase extends CourseUpdateUseCase {
    public UpdateLessonsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Lesson> lessons = super.mapLessons(input.getLessons());
        return course.updateLessons(lessons, input.getSectionId());
    }
}
