package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public class StudentGetRegisteredCoursesUseCase extends UseCase<
        StudentGetRegisteredCoursesUseCase.InputValues, StudentGetRegisteredCoursesUseCase.OutputValues>{
    private final CourseStudentRepository courseStudentRepository;

    public StudentGetRegisteredCoursesUseCase(CourseStudentRepository courseStudentRepository) {
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(courseStudentRepository.findCoursesByStudent(input.getUserId()));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<CourseStudent> courses;
    }
}
