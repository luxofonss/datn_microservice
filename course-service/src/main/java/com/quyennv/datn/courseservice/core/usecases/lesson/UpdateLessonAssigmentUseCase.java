package com.quyennv.datn.courseservice.core.usecases.lesson;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.repositories.LessonRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Value;

public class UpdateLessonAssigmentUseCase extends UseCase<
        UpdateLessonAssigmentUseCase.InputValues, UpdateLessonAssigmentUseCase.OutputValues> {
    private final LessonRepository lessonRepository;

    public UpdateLessonAssigmentUseCase(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Lesson lesson = lessonRepository.findById(input.getLessonId()).orElseThrow(
                () -> new RuntimeException("No lesson found")
        );

        lesson.updateAssignment(input.getAssignmentId());
        return new OutputValues(lessonRepository.persist(lesson).getId());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity lessonId;
        Identity assignmentId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Identity lessonId;
    }
}
