package com.quyennv.datn.assignment_service.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudentJoinCourseByCodeUseCase extends UseCase<StudentJoinCourseByCodeUseCase.InputValues, StudentJoinCourseByCodeUseCase.OutputValues>{
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public StudentJoinCourseByCodeUseCase(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = courseRepository.findByCode(input.getCode()).orElseThrow(() ->
                new RuntimeException("Not found"));

        LocalDateTime now = LocalDateTime.now();

        // check if course has expires time and now is between start and end time
        if (Objects.nonNull(course.getStartDate()) && now.isBefore(course.getStartDate())
                || Objects.nonNull(course.getEndDate()) && now.isAfter(course.getEndDate())
        ) {
            throw new RuntimeException("Course expired or not started yet!");
        }

        return new OutputValues(
                createCourseStudent(input, course)
        );
    }

    private CourseStudent createCourseStudent(InputValues input, Course course) {

        return courseStudentRepository.persist(
                CourseStudent
                        .builder()
                        .course(course)
                        .studentId(input.getRequester())
                        .price(0)
                        .status(EnrollStatus.PENDING)
                        .build()
        );
    }


    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String code;
        Identity requester;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues{
        CourseStudent courseStudent;
    }
}
