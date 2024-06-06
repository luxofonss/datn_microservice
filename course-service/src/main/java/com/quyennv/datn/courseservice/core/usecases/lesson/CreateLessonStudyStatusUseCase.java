package com.quyennv.datn.courseservice.core.usecases.lesson;

import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.core.repositories.LessonStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;
import com.quyennv.datn.courseservice.core.domain.enums.LessonStudentStatus;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class CreateLessonStudyStatusUseCase extends UseCase<CreateLessonStudyStatusUseCase.InputValues,
        CreateLessonStudyStatusUseCase.OutputValues> {
    private final LessonStudentRepository lessonStudentRepository;

    public CreateLessonStudyStatusUseCase(LessonStudentRepository lessonStudentRepository) {
        this.lessonStudentRepository = lessonStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        LessonStudent data = LessonStudent
                .builder()
                .lesson(Lesson.builder().id(Identity.fromString(input.lessonId)).build())
                .student(User.builder().id(Identity.fromString(input.studentId)).build())
                .status(input.getStatus() != null ? LessonStudentStatus.valueOf(input.getStatus()) : LessonStudentStatus.DOING)
                .build();
        return new OutputValues(lessonStudentRepository.save(data));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String lessonId;
        String studentId;
        String status;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        LessonStudent data;
    }
}
