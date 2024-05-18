package com.quyennv.datn.assignment_service.presenter.mapper.course;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.UpdateCourseRequest;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import com.quyennv.datn.courseservice.presenter.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class CourseMutationDetailInputMapper extends UpdateCourseUseCaseInputMapper{
//    @Override
    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, UpdateCourseRequest req, String courseId) {
        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .name(req.getName())
                .description(req.getDescription())
                .backgroundImage(req.getBackgroundImage())
                .thumbnail(req.getThumbnail())
                .startDate(Objects.nonNull(req.getStartDate()) ? DateHelper.toLocalDateTime(req.getStartDate()) : null)
                .endDate(Objects.nonNull(req.getEndDate()) ? DateHelper.toLocalDateTime(req.getEndDate()) : null)
                .price(req.getPrice())
                .level(Objects.nonNull(req.getLevel()) ? CourseLevel.valueOf(req.getLevel()) : null)
                .grade(req.getGrade())
                .requesterId(Objects.nonNull(requester.getId()) ? Identity.from(requester.getId()) : null)
                .subjectId(Objects.nonNull(req.getSubjectId()) ? Identity.fromString(req.getSubjectId()) : null)
                .courseInfos(mapInfos(req.getCourseInfos()))
                .students(mapStudents(req.getStudents()))
                .sections(mapSections(req.getSections()))
                .build();
    }

    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, String courseId) {
        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .requesterId(Objects.nonNull(requester.getId()) ? Identity.from(requester.getId()) : null)
                .build();
    }
}
