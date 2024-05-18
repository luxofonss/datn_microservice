package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Section;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course persist(Course course);
    Optional<Course> findById(Identity id);
    Optional<Course> findByCode(String code);
    List<Course> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherId
    );

    List<Section> getAllSectionInCourse(Identity courseId);
}
