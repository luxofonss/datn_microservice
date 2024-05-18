package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseData;
import com.quyennv.datn.courseservice.adapter.db.postgres.entities.SectionData;
import com.quyennv.datn.courseservice.adapter.db.postgres.repositories.CriteriaCourseRepository;
import com.quyennv.datn.courseservice.adapter.db.postgres.repositories.JpaSectionRepository;
import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Section;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CourseRepositoryImpl implements CourseRepository {
    private final JpaCourseRepository jpaCourseRepository;
    private final JpaSectionRepository jpaSectionRepository;
    private final CriteriaCourseRepository criteriaCourseRepository;

    public CourseRepositoryImpl(JpaCourseRepository jpaCourseRepository,
                                JpaSectionRepository jpaSectionRepository,
                                CriteriaCourseRepository criteriaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
        this.jpaSectionRepository = jpaSectionRepository;
        this.criteriaCourseRepository = criteriaCourseRepository;
    }

    @Override
    @Transactional
    public Course persist(Course course) {
        CourseData courseData = CourseData.from(course);
        return jpaCourseRepository.save(courseData).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Identity id) {
        return jpaCourseRepository.findById(id.getId()).map(CourseData::fromThis);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findByCode(String code) {
        return jpaCourseRepository.findByCode(code).map(CourseData::fromThis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherIds) {
        return criteriaCourseRepository.getWithFilters(
                keyword, level, grade, code, teacherIds
        ).stream().map(CourseData::fromThis).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Section> getAllSectionInCourse(Identity courseId) {
        return jpaSectionRepository.findByCourseId(courseId.getId()).stream().map(SectionData::fromThis).toList();
    }
}
