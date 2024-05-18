package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class RegisterForCourseUseCase extends UseCase<
        RegisterForCourseUseCase.InputValues, RegisterForCourseUseCase.OutputValues> {
    private final CourseRepository courseRepository;

    protected RegisterForCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = courseRepository.findById(input.getCourseId()).orElseThrow(() ->
                new RuntimeException("Not found"));

        LocalDateTime now = LocalDateTime.now();

        // check if course has expires time and now is between start and end time
        if (Objects.nonNull(course.getStartDate()) && now.isBefore(course.getStartDate())
                || Objects.nonNull(course.getEndDate()) && now.isAfter(course.getEndDate())
        ) {
            throw new RuntimeException("Course expired or not started yet!");
        }

        return new OutputValues(
                createCourseStudents(input)
        );
    }

    public abstract List<CourseStudent> createCourseStudents(InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        List<String> emails;
        Identity courseId;
        EnrollStatus status;
        Integer price;
        Identity requesterId;
        String requestToken;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues{
        List<CourseStudent> courseStudents;
    }
}
