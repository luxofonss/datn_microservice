package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAssignmentInCourseUseCase extends GetAssignmentsUseCase{
    public GetAssignmentInCourseUseCase(AssignmentRepository assignmentRepository) {
        super(assignmentRepository);
    }

    @Override
    public List<Assignment> getAssignments(InputValues input) {
        if (Objects.nonNull(input.getCourseId())) {
//            List<Section> sections = courseRepository.getAllSectionInCourse(input.getCourseId());
//
//            if (!sections.isEmpty()) {
//                return assignmentRepository.findAllWithFilter(
//                        input.getTitle(),
//                        input.getTeacherId(),
//                        input.getSubjectId(),
//                        input.getCourseId(),
//                        sections.stream().map(Section::getId).toList(),
//                        input.getStudentId()
//                );
//            } else {
                return new ArrayList<>();
//            }
        }

        return assignmentRepository.findAllWithFilter(
                input.getTitle(),
                input.getTeacherId(),
                input.getSubjectId(),
                null,
                input.getLessonId() != null ? List.of(input.getLessonId()) : null,
                input.getStudentId()
        );
    }
}
