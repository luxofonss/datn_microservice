package com.quyennv.datn.assignment_service.core.usecases.coure;

import com.quyennv.datn.courseservice.core.domain.entities.*;
import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.domain.enums.CourseStatus;
import com.quyennv.datn.courseservice.core.domain.valueobject.Resource;
import com.quyennv.datn.courseservice.core.domain.valueobject.User;
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
public class CreateCourseUseCase extends UseCase<CreateCourseUseCase.InputValues, CreateCourseUseCase.OutputValues> {
    private final CourseRepository courseRepository;

    public CreateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = createCourse(input);
        return new OutputValues(courseRepository.persist(course));
    }

    private Course createCourse(InputValues input) {
        Course course = Course
                .builder()
                .id(Identity.newIdentity())
                .name(input.getName())
                .description(input.getDescription())
                .backgroundImage(input.getBackgroundImage())
                .thumbnail(input.getThumbnail())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .price(input.getPrice())
                .level(input.getLevel())
                .status(CourseStatus.DRAFT)
                .grade(input.getGrade())
                .teacher(User.builder().id(input.getCreatorId()).build())
                .subject(Subject.builder().id(input.getSubjectId()).build())
//                .code("random")
                .build();

        if (Objects.nonNull(input.getCourseInfos())) {
            List<CourseInfo> courseInfos = input.getCourseInfos().stream().map(
                    i -> CourseInfo
                                .builder()
                                .content(i.getContent())
                                .type(i.getType())
                                .build()
            ).toList();
            course.setCourseInfos(courseInfos);
        }

        if (Objects.nonNull(input.getSections())) {
            List<Section> sections = input.getSections().stream().map(
                    s -> {
                        List<Lesson> lessons = new ArrayList<>();
                        if (Objects.nonNull(s.getLessons())) {
                            lessons.addAll(s.getLessons().stream().map(
                                    l -> Lesson
                                            .builder()
                                            .name(l.getName())
                                            .description(l.getDescription())
                                            .resource(l.getResource())
                                            .order(l.getOrder())
                                            .build()
                            ).toList());
                        }

                        return Section
                                .builder()
                                .name(s.getName())
                                .description(s.getDescription())
                                .lessons(lessons)
                                .build();
                    }
            ).toList();

            course.setSections(sections);
        }

        return course;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
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
        Identity creatorId;
        List<CourseInfoInput> courseInfos;
        List<CourseSection> sections;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Course course;
    }

    @Value
    @Builder
    public static class CourseInfoInput {
        String content;
        CourseInfoType type;
    }

    @Value
    @Builder
    public static class CourseSection {
        String name;
        String description;
        List<CourseLesson> lessons;
    }

    @Value
    @Builder
    public static class CourseLesson {
        String name;
        String description;
        Resource resource;
        Integer order;
    }
}
