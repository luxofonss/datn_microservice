package com.quyennv.datn.courseservice.presenter.mapper.course;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class UpdateCourseUseCaseInputMapper {
    public static List<CourseUpdateUseCase.CourseInfoInput> mapInfos(List<UpdateCourseRequestCourseInfo> infos) {
        if (Objects.nonNull(infos)) {
            return infos.stream().map(
                    i -> CourseUpdateUseCase.CourseInfoInput
                            .builder()
                            .id(Objects.nonNull(i.getId()) ? Identity.fromString(i.getId()) : null)
                            .content(i.getContent())
                            .type(CourseInfoType.valueOf(i.getType()))
                            .build()
            ).toList();
        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseSection> mapSections(List<UpdateCourseRequestSection> sections) {
        if (Objects.nonNull(sections)) {
            return sections.stream().map(
                    s -> CourseUpdateUseCase.CourseSection
                            .builder()
                            .id(Objects.nonNull(s.getId()) ? Identity.fromString(s.getId()) : null)
                            .name(s.getName())
                            .description(s.getDescription())
                            .lessons(mapLessons(s.getLessons()))
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseLesson> mapLessons(List<UpdateCourseRequestLesson> lessons) {
        if (Objects.nonNull(lessons)) {
            return lessons.stream().map(
                    l -> CourseUpdateUseCase.CourseLesson
                            .builder()
                            .id(Objects.nonNull(l.getId()) ? Identity.fromString(l.getId()) : null)
                            .name(l.getName())
                            .type(l.getType())
                            .order(l.getOrder())
                            .description(l.getDescription())
                            .resourceId(Objects.nonNull(l.getResourceId()) ? Identity.fromString(l.getResourceId()) : null)
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseStudent> mapStudents(List<UpdateCourseRequestCourseStudent> students) {
        if (Objects.nonNull(students)) {
            return students.stream().map(
                    s -> CourseUpdateUseCase.CourseStudent
                            .builder()
                            .studentId(Identity.fromString(s.getStudentId()))
                            .status(EnrollStatus.valueOf(s.getStatus()))
                            .price(s.getPrice())
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }
}
