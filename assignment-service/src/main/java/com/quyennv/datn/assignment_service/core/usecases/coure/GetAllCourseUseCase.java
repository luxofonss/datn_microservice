package com.quyennv.datn.assignment_service.core.usecases.coure;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;

import java.util.List;

public class GetAllCourseUseCase extends GetCoursesUseCase{
    public GetAllCourseUseCase(CourseRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> getCourses(InputValues input) {
        return courseRepository.getWithFilters(
                input.getKeyword(),
                input.getLevel(),
                input.getGrade(),
                input.getCode(),
                input.getTeacherIds()
        );
    }
}
