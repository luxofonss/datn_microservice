package com.quyennv.datn.courseservice.core.usecases.coure;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public abstract class GetCoursesUseCase extends UseCase<
        GetCoursesUseCase.InputValues, GetCoursesUseCase.OutputValues> {
    public final CourseRepository courseRepository;

    protected GetCoursesUseCase(CourseRepository repository) {
        this.courseRepository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(getCourses(input));
    }

    public abstract List<Course> getCourses(InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String keyword;
        CourseLevel level;
        Integer grade;
        String code;
        List<Identity> teacherIds;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Course> courses;
    }
}
