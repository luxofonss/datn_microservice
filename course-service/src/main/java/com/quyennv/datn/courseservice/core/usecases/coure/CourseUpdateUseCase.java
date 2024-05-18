package com.quyennv.datn.courseservice.core.usecases.coure;


import com.quyennv.datn.courseservice.core.domain.entities.*;
import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.domain.enums.LessonType;
import com.quyennv.datn.courseservice.core.domain.valueobject.Resource;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class CourseUpdateUseCase extends UseCase<CourseUpdateUseCase.InputValues, CourseUpdateUseCase.OutputValues> {
    public final CourseRepository courseRepository;

    protected CourseUpdateUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course existCourse = courseRepository.findById(input.getCourseId()).orElseThrow(
                () -> new RuntimeException("No course found")
        );

        if (!existCourse.getTeacher().getId().equals(input.getRequesterId())) {
            throw new RuntimeException("No permission");
        }

        Course course = update(existCourse, input);
        return persistAndReturn(course);
    }

    private OutputValues persistAndReturn(Course course) {
        return new OutputValues(
                courseRepository.persist(course)
        );
    }

    protected abstract Course update(Course course, InputValues input);

    public List<Section> mapSection(List<CourseSection> courseSections) {
        return courseSections.stream().map(
                section -> {
                    Section returnSection = Section
                            .builder()
                            .id(section.getId())
                            .name(section.getName())
                            .description(section.getDescription())
                            .build();
                    List<Lesson> lessons = new ArrayList<>();
                    if (Objects.nonNull(section.getLessons())) {
                        lessons.addAll(mapLessons(section.getLessons()));
                    }
                    returnSection.setLessons(lessons);

                    return returnSection;
                }
        ).toList();
    }

    public List<CourseInfo> mapCourseInfos(List<CourseInfoInput> courseInfoInputs) {
        return courseInfoInputs.stream().map(i ->
                CourseInfo
                        .builder()
                        .id(i.getId())
                        .type(i.getType())
                        .content(i.getContent())
                        .build()
        ).toList();
    }

    protected List<Lesson> mapLessons(List<CourseLesson> lessons) {
        return lessons.stream().map(
                l -> Lesson
                        .builder()
                        .id(l.getId())
                        .name(l.getName())
                        .type(l.getType())
                        .order(l.getOrder())
                        .description(l.getDescription())
                        .resource(Objects.nonNull(l.getResourceId()) ? Resource.builder().id(l.getResourceId()).build() : null)
                        .build()
        ).toList();
    }

    @Value
    @Builder
    public static class InputValues implements  UseCase.InputValues {
        Identity courseId;
        Identity sectionId;
        Identity lessonId;
        Identity requesterId;
        String name;
        String description;
        String backgroundImage;
        String thumbnail;
        LocalDateTime startDate;
        LocalDateTime endDate;
        Long price;
        CourseLevel level;
        Integer grade;
        Identity subjectId;
        List<CourseInfoInput> courseInfos;
        List<CourseSection> sections;
        List<CourseStudent> students;
        List<CourseLesson> lessons;
    }

    @Value
    public static class OutputValues implements  UseCase.OutputValues {
        Course course;
    }

    @Value
    @Builder
    public static class CourseInfoInput {
        Identity id;
        String content;
        CourseInfoType type;
    }

    @Value
    @Builder
    public static class CourseSection {
        Identity id;
        String name;
        String description;
        List<CourseLesson> lessons;
    }

    @Value
    @Builder
    public static class CourseLesson {
        Identity id;
        LessonType type;
        String name;
        Integer order;
        String description;
        Identity resourceId;
    }

    @Value
    @Builder
    public static class CourseStudent {
        Identity studentId;
        EnrollStatus status;
        Integer price;
    }
}
