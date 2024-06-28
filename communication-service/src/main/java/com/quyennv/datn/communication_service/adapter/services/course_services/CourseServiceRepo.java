package com.quyennv.datn.communication_service.adapter.services.course_services;

import com.quyennv.datn.communication_service.core.domain.valueobject.Course;
import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "course-service",
        url = "${app.services.course}")
public interface CourseServiceRepo {
    @GetMapping(value = "/internal/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Course> getCourseById(@PathVariable("courseId") String courseId);

}
