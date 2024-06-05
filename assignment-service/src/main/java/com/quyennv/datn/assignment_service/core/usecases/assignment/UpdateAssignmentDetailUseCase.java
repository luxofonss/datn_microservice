package com.quyennv.datn.assignment_service.core.usecases.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import com.quyennv.datn.assignment_service.core.repositories.UpdateAssignmentUseCase;

import java.util.List;
import java.util.Objects;

public class UpdateAssignmentDetailUseCase extends UpdateAssignmentUseCase {
    public UpdateAssignmentDetailUseCase(AssignmentRepository assignmentRepository) {
        super(assignmentRepository);
    }

    @Override
    public Assignment update(InputValues input, Assignment assignment) {
        Assignment updateAssignment = Assignment
                .builder()
                .id(input.getAssignmentId())
                .title(input.getTitle())
                .description(input.getDescription())
                .createdBy(assignment.getCreatedBy())
                .totalMark(assignment.getTotalMark())
                .subjectId(assignment.getSubjectId())
                .duration(input.getDuration())
                .startTime(input.getStartTime())
                .endTime(input.getEndTime())
                .assignmentType(input.getAssignmentType())
                .maxAttemptTimes(input.getMaxAttemptTimes())
                .build();

        if (Objects.nonNull(input.getQuestions()) && !input.getQuestions().isEmpty()) {
            List<Question> questions = mapQuestions(input.getQuestions(), input);
            updateAssignment.setQuestions(questions);
        }

        return assignment.update(updateAssignment);
    }
}
