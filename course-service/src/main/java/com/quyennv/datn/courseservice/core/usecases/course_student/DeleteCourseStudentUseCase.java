package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;

public class DeleteCourseStudentUseCase extends UpdateCourseStudentUseCase{
    public DeleteCourseStudentUseCase(CourseStudentRepository courseStudentRepository) {
        super(courseStudentRepository);
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent, InputValues input) {
        return courseStudent.delete();
    }
}
