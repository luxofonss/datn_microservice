package com.quyennv.datn.assignment_service.presenter.mapper.section;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.UpdateCourseRequest;
import com.quyennv.datn.courseservice.presenter.mapper.course.UpdateCourseUseCaseInputMapper;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SectionMutationInputMapper extends UpdateCourseUseCaseInputMapper {
//    @Override
    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, UpdateCourseRequest req, String courseId) {

        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .sections(mapSections(req.getSections()))
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
