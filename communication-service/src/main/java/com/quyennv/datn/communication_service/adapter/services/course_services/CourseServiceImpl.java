package com.quyennv.datn.communication_service.adapter.services.course_services;

import com.quyennv.datn.communication_service.core.domain.valueobject.Course;
import com.quyennv.datn.communication_service.core.usecases.notification.CourseService;
import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseServiceRepo courseServiceRepo;

    public CourseServiceImpl(CourseServiceRepo courseServiceRepo) {
        this.courseServiceRepo = courseServiceRepo;
    }

    @Override
    public Course getCourseById(String courseId) {
        ApiResponse<Course> response = courseServiceRepo.getCourseById(courseId);
        
        return response.getData();
    }
}
