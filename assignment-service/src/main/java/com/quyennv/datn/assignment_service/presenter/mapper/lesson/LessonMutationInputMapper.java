package com.quyennv.datn.assignment_service.presenter.mapper.lesson;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.UpdateCourseRequest;
import com.quyennv.datn.courseservice.presenter.mapper.course.UpdateCourseUseCaseInputMapper;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;

public class LessonMutationInputMapper extends UpdateCourseUseCaseInputMapper {
    public static CourseUpdateUseCase.InputValues map(
            UserPrincipal requester,
            UpdateCourseRequest req,
            String courseId,
            String sectionId) {

        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .sectionId(Identity.fromString(sectionId))
                .lessons(mapLessons(req.getLessons()))
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
