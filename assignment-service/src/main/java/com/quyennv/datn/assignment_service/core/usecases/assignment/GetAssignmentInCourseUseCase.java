package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Section;
import com.quyennv.lms.core.usecases.course.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAssignmentInCourseUseCase extends GetAssignmentsUseCase{
    private final CourseRepository courseRepository;
    public GetAssignmentInCourseUseCase(AssignmentRepository assignmentRepository,
                                           CourseRepository courseRepository) {
        super(assignmentRepository);
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Assignment> getAssignments(InputValues input) {
        if (Objects.nonNull(input.getCourseId())) {
            List<Section> sections = courseRepository.getAllSectionInCourse(input.getCourseId());

            if (!sections.isEmpty()) {
                return assignmentRepository.findAllWithFilter(
                        input.getTitle(),
                        input.getTeacherId(),
                        input.getSubjectId(),
                        input.getCourseId(),
                        sections.stream().map(Section::getId).toList(),
                        input.getStudentId()
                );
            } else {
                return new ArrayList<>();
            }
        }

        return assignmentRepository.findAllWithFilter(
                input.getTitle(),
                input.getTeacherId(),
                input.getSubjectId(),
                null,
                Objects.nonNull(input.getSectionId()) ? List.of(input.getSectionId()) : null,
                input.getStudentId()
        );
    }
}
