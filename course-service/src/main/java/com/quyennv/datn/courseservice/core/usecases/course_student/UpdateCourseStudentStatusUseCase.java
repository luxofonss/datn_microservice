package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;

public class UpdateCourseStudentStatusUseCase extends UpdateCourseStudentUseCase {
    public UpdateCourseStudentStatusUseCase(CourseStudentRepository courseStudentRepository) {
        super(courseStudentRepository);
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent, InputValues input) {
        EnrollStatus status = courseStudent.getStatus();
        if (courseStudent.getStatus().equals(input.getStatus())) {
            throw new RuntimeException("Status is already " + status);
        }

        return courseStudent.withStatus(input.getStatus());
    }

}
