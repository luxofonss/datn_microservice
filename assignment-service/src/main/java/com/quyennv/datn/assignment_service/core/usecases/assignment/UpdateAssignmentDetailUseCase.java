package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.domain.entities.Subject;
import com.quyennv.lms.core.domain.entities.User;

import java.util.List;
import java.util.Objects;

public class UpdateAssignmentDetailUseCase extends UpdateAssignmentUseCase{
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
                .creator(Objects.nonNull(input.getTeacherId()) ? User.builder().id(input.getTeacherId()).build() : null)
                .totalMark(assignment.getTotalMark())
                .subject(Objects.nonNull(input.getSubjectId()) ? Subject.builder().id(input.getSubjectId()).build() : null)
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
