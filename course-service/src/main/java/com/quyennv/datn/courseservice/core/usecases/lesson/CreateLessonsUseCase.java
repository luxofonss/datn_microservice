package com.quyennv.datn.courseservice.core.usecases.lesson;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;

import java.util.List;

public class CreateLessonsUseCase extends CourseUpdateUseCase {
    public CreateLessonsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Lesson> lessons = super.mapLessons(input.getLessons());

        return course.addLessons(lessons, input.getSectionId());
    }
}
