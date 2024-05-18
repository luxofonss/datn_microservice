package com.quyennv.datn.assignment_service.core.usecases.coure;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.UseCase;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GetOneCourseUseCase extends UseCase<GetOneCourseUseCase.InputValues, GetOneCourseUseCase.OutputValues> {
    private final CourseRepository courseRepository;

    public GetOneCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = courseRepository.findById(input.getCourseId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );

        if (course.getSections() != null
                && !course.getSections().isEmpty()
                && !input.getRequesterId().equals(course.getTeacher().getId())) {
            course.getSections().forEach(section -> {
                if (section.getLessons() != null && !section.getLessons().isEmpty()) {
                    section.setLessons(section.getLessons().stream().map(lesson -> {
                        if (lesson.getLessonStudents() != null && !lesson.getLessonStudents().isEmpty()) {
                            lesson.getLessonStudents().forEach(lessonStudent -> {
                                if (lessonStudent.getStudent().getId().equals(input.getRequesterId())) {
                                    lesson.setLessonStudent(lessonStudent);
                                }
                            });
                        }

                        return lesson;
                    }).toList());
                }
            });
        }

        return new OutputValues(course);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity courseId;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Course course;
    }
}
