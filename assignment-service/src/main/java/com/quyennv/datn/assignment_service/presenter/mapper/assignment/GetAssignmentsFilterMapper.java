package com.quyennv.datn.assignment_service.presenter.mapper.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.usecases.assignment.GetAssignmentsUseCase;
import com.quyennv.datn.assignment_service.presenter.dto.assignment.GetAssignmentFilters;
import com.quyennv.datn.assignment_service.presenter.usecases.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class GetAssignmentsFilterMapper {
    public static GetAssignmentsUseCase.InputValues map(GetAssignmentFilters filters, UserPrincipal requester) {
        return GetAssignmentsUseCase.InputValues.builder()
                .title(filters.getTitle())
                .subjectId(Objects.nonNull(filters.getSubjectId()) ? Identity.fromString(filters.getSubjectId()) : null)
                .courseId(Objects.nonNull(filters.getCourseId()) ? Identity.fromString(filters.getCourseId()) : null)
                .lessonId(Objects.nonNull(filters.getLessonId()) ? Identity.fromString(filters.getLessonId()) : null)
                .build();
    }
}
