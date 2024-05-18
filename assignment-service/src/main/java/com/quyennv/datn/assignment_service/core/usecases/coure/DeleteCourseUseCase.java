package com.quyennv.datn.assignment_service.core.usecases.coure;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;

public class DeleteCourseUseCase extends CourseUpdateUseCase{
    public DeleteCourseUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        return course.delete();
    }
}
