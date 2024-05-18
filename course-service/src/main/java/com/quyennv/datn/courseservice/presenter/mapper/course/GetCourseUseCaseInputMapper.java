package com.quyennv.datn.courseservice.presenter.mapper.course;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.usecases.coure.GetCoursesUseCase;
import com.quyennv.datn.courseservice.presenter.dto.course.GetCoursesRequest;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

@Slf4j
public class GetCourseUseCaseInputMapper {
    public static GetCoursesUseCase.InputValues map(GetCoursesRequest request, UserPrincipal requester) {
         return GetCoursesUseCase.InputValues
                .builder()
                .keyword(request.getKeyword())
                .level(Objects.nonNull(request.getLevel()) ? CourseLevel.valueOf(request.getLevel()) : null)
                .grade(request.getGrade())
                .code(request.getCode())
                .teacherIds(Objects.nonNull(request.getTeacherIds())
                        ? request.getTeacherIds().stream().map(Identity::fromString).toList()
                        : null)
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
