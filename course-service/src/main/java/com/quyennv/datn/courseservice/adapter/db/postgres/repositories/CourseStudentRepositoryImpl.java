package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseStudentData;
import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseStudentDataKey;
import com.quyennv.datn.courseservice.core.domain.entities.CourseStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.repositories.CourseStudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CourseStudentRepositoryImpl implements CourseStudentRepository {
    private final JpaCourseStudentRepository jpaCourseStudentRepository;
    private final EntityManager em;
    private final CriteriaBuilder builder;

    public CourseStudentRepositoryImpl(JpaCourseStudentRepository jpaCourseStudentRepository, EntityManager em) {
        this.jpaCourseStudentRepository = jpaCourseStudentRepository;
        this.em = em;
        this.builder = em.getCriteriaBuilder();
    }

    @Override
    @Transactional
    public CourseStudent persist(CourseStudent courseStudent) {
        CourseStudentData courseStudentData = CourseStudentData.from(courseStudent);
        courseStudentData.setCourseId(courseStudent.getCourse().getId().getUUID());
        courseStudentData.setStudentId(courseStudent.getStudentId().getUUID());
        return jpaCourseStudentRepository.save(courseStudentData).fromThis();
    }

    @Override
    public Optional<CourseStudent> findByCourseAndStudent(Identity courseId, Identity studentId) {
        return jpaCourseStudentRepository.findByCourseIdAndStudentId(courseId.getId(), studentId.getId()).map(CourseStudentData::fromThis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> findByCourse(Identity courseId) {
        return jpaCourseStudentRepository.findByCourseId(courseId.getId()).stream().map(CourseStudentData::fromThis).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> findCoursesByStudent(Identity studentId) {
        CriteriaQuery<CourseStudentData> cq = builder.createQuery(CourseStudentData.class);
        Root<CourseStudentData> root = cq.from(CourseStudentData.class);

        cq.where(builder.equal(root.get("studentId"), studentId.getId()));
        return em.createQuery(cq).getResultList().stream().map(CourseStudentData::fromThis).toList();
    }
}
