package com.quyennv.datn.communication_service.core.usecases.notification;

import com.quyennv.datn.communication_service.core.domain.valueobject.Course;

public interface CourseService {
    Course getCourseById(String courseId);
}
