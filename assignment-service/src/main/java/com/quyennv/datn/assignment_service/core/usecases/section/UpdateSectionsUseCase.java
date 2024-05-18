package com.quyennv.datn.assignment_service.core.usecases.section;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Section;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;

import java.util.List;

public class UpdateSectionsUseCase extends CourseUpdateUseCase {
    public UpdateSectionsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Section> sections = super.mapSection(input.getSections());
        return course.updateSections(sections);
    }
}
