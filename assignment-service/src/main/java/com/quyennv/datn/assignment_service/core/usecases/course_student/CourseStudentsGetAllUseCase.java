package com.quyennv.datn.assignment_service.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public class CourseStudentsGetAllUseCase extends UseCase<
        CourseStudentsGetAllUseCase.InputValues, CourseStudentsGetAllUseCase.OutputValues> {
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentsGetAllUseCase(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(courseStudentRepository.findByCourse(input.courseId));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity requesterId;
        Identity courseId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<CourseStudent> students;
    }
}
