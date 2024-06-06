package com.quyennv.datn.courseservice.core.usecases.course_student;

import com.quyennv.datn.courseservice.adapter.services.user_services.UserService;
import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import com.quyennv.datn.courseservice.core.repositories.CourseUserRepository;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.presenter.dto.ApiResponse;
import com.quyennv.datn.courseservice.presenter.dto.user.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TeacherAddStudentsToCourseUseCase extends RegisterForCourseUseCase{
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final CourseStudentRepository courseStudentRepository;
//    private final CourseUserRepository courseUserRepository;

    public TeacherAddStudentsToCourseUseCase(CourseRepository courseRepository,
                                             CourseStudentRepository courseStudentRepository,
                                             UserService userService) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.userService = userService;
    }

    @Override
    public List<CourseStudent> createCourseStudents(InputValues input) {
        ApiResponse<List<UserResponse>> res = userService.getAllUsers(input.getRequestToken(), input.getEmails());

        if (res.getData() == null || res.getData().isEmpty()) {
            return new ArrayList<>();
        }

        log.info("res:: {}", res);

        return res.getData().stream().map(
                user -> courseStudentRepository.persist(
                        CourseStudent
                                .builder()
                                .course(Course.builder().id(input.getCourseId()).build())
                                .studentId(Identity.fromString(user.getId()))
                                .student(User.builder().id(Identity.fromString(user.getId())).build())
                                .status(EnrollStatus.ACTIVE)
                                .build()
                )
        ).toList();
    }
}
