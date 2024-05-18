package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StudentRegisterForCourseUseCase extends RegisterForCourseUseCase{
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public StudentRegisterForCourseUseCase(CourseRepository repository, CourseStudentRepository courseStudentRepository) {
        super(repository);
        this.courseRepository = repository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public List<CourseStudent> createCourseStudents(InputValues input) {
        return List.of(courseStudentRepository.persist(
                CourseStudent
                        .builder()
                        .course(Course.builder().id(input.getCourseId()).build())
                        .studentId(input.getRequesterId())
                        .price(input.getPrice())
                        .status(EnrollStatus.PENDING)
                        .build()
        ));
    }
}
