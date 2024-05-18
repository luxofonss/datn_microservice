package com.quyennv.datn.courseservice.presenter.mapper.course;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.domain.valueobject.Resource;
import com.quyennv.datn.courseservice.core.usecases.coure.CreateCourseUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.CreateCourseRequest;
import com.quyennv.datn.courseservice.presenter.dto.course.CreateCourseRequestCourseInfo;
import com.quyennv.datn.courseservice.presenter.dto.course.CreateCourseRequestLesson;
import com.quyennv.datn.courseservice.presenter.dto.course.CreateCourseRequestSection;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import com.quyennv.datn.courseservice.presenter.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CreateCourseUseCaseInputMapper {
    public static CreateCourseUseCase.InputValues map(UserPrincipal requester, CreateCourseRequest req) {
        return CreateCourseUseCase.InputValues
                .builder()
                .name(req.getName())
                .description(req.getDescription())
                .backgroundImage(req.getBackgroundImage())
                .thumbnail(req.getThumbnail())
                .startDate(Objects.nonNull(req.getStartDate()) ? DateHelper.toLocalDateTime(req.getStartDate()) : null)
                .endDate(Objects.nonNull(req.getEndDate()) ?  DateHelper.toLocalDateTime(req.getEndDate()) : null)
                .price(req.getPrice())
                .level(Objects.nonNull(req.getLevel()) ? CourseLevel.valueOf(req.getLevel()) : null)
                .grade(req.getGrade())
                .subjectId(Objects.nonNull(req.getSubjectId()) ? Identity.fromString(req.getSubjectId()) : null)
                .creatorId(Identity.from(requester.getId()))
                .courseInfos(Objects.nonNull(req.getCourseInfos()) ? mapInfos(req.getCourseInfos()) : null)
                .sections(Objects.nonNull(req.getSections()) ? mapSections(req.getSections()) : null)
                .build();
    }

    private static List<CreateCourseUseCase.CourseInfoInput> mapInfos(List<CreateCourseRequestCourseInfo> infos) {
        if (Objects.nonNull(infos)) {
            return infos.stream().map(
                    i -> CreateCourseUseCase.CourseInfoInput
                            .builder()
                            .content(i.getContent())
                            .type(CourseInfoType.valueOf(i.getType()))
                            .build()
            ).toList();
        } else {
            return new ArrayList<>();
        }
    }

    private static List<CreateCourseUseCase.CourseSection> mapSections(List<CreateCourseRequestSection> sections) {
        if (Objects.nonNull(sections)) {
            return sections.stream().map(
                    s -> CreateCourseUseCase.CourseSection
                            .builder()
                            .name(s.getName())
                            .description(s.getDescription())
                            .lessons(mapLessons(s.getLessons()))
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    private static List<CreateCourseUseCase.CourseLesson> mapLessons(List<CreateCourseRequestLesson> lessons) {
        if (Objects.nonNull(lessons)) {
            return lessons.stream().map(
                    l -> CreateCourseUseCase.CourseLesson
                            .builder()
                            .name(l.getName())
                            .resource(Objects.nonNull(l.getResourceId())
                                    ? mapResource(l.getResourceId())
                                    : null)
                            .description(l.getDescription())
                            .order(l.getOrder())
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    private static Resource mapResource(String resourceId) {
        return Resource
                .builder()
                .id(Identity.fromString(resourceId))
                .build();
    }
}
