package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.GetAssignmentsUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.GetAssignmentFilters;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class GetAssignmentsFilterMapper {
    public static GetAssignmentsUseCase.InputValues map(GetAssignmentFilters filters, UserPrincipal requester) {
        return GetAssignmentsUseCase.InputValues.builder()
                .title(filters.getTitle())
                .subjectId(Objects.nonNull(filters.getSubjectId()) ? Identity.fromString(filters.getSubjectId()) : null)
                .courseId(Objects.nonNull(filters.getCourseId()) ? Identity.fromString(filters.getCourseId()) : null)
                .sectionId(Objects.nonNull(filters.getSectionId()) ? Identity.fromString(filters.getSectionId()) : null)
                .build();
    }
}
