package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.CourseData;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CriteriaCourseRepository {
    private final EntityManager em;
    private final CriteriaBuilder cb;

    public CriteriaCourseRepository(EntityManager em) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
    }

    public List<CourseData> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherIds
    ) {
        CriteriaQuery<CourseData> criteriaQuery = cb.createQuery(CourseData.class);
        Root<CourseData> courseRoot = criteriaQuery.from(CourseData.class);

        Predicate predicate = getPredicate(
                keyword,
                level,
                grade,
                code,
                teacherIds,
                courseRoot
        );

        criteriaQuery.where(predicate);
        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(
                courseRoot.get("updatedAt")
        ));
        criteriaQuery.orderBy(orders);
        TypedQuery<CourseData> courseQuery = em.createQuery(criteriaQuery);

        return courseQuery.getResultList();
    }

    private Predicate getPredicate(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherIds,
            Root<CourseData> courseRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(keyword)) {
            predicates.add(
                    cb.or(
                            cb.like(courseRoot.get("name"), "%" + keyword + "%"),
                            cb.like(courseRoot.get("description"), "%" + keyword + "%")
                    )
            );
        }

        if (Objects.nonNull(level)) {
            predicates.add(cb.equal(courseRoot.get("level"), level.toString()));
        }

        if (Objects.nonNull(grade)) {
            predicates.add(cb.equal(courseRoot.get("grade"), grade));
        }

        if (Objects.nonNull(code)) {
            predicates.add(cb.equal(courseRoot.get("code"), code));
        }

        if (Objects.nonNull(code)) {
            predicates.add(cb.equal(courseRoot.get("code"), code));
        }

        if(Objects.nonNull(teacherIds)) {
            List<Predicate> teacherPredicates = new ArrayList<>();
            teacherIds.forEach(userId -> teacherPredicates.add(cb.equal(
                    courseRoot.get("teacher").get("id"), userId.getId())
            ));

            predicates.add(cb.or(
                    teacherPredicates.toArray(new Predicate[0])
            ));
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
