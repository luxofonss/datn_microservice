package com.quyennv.datn.assignment_service.core.usecases.section;

import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import com.quyennv.datn.courseservice.core.usecases.coure.CourseUpdateUseCase;
import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Section;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CreateSectionsUseCase extends CourseUpdateUseCase {
    public CreateSectionsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Section> sections = super.mapSection(input.getSections());
        log.info("Creating sections: {}", sections);
        return course.addSections(sections);
    }
}
