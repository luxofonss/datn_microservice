package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentData;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CriteriaAssignmentRepository {
    private final EntityManager em;
    private final CriteriaBuilder cb;

    public CriteriaAssignmentRepository(EntityManager em) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
    }

    @Transactional(readOnly = true)
    public List<AssignmentData> findAllWithFilter(
            String title,
            Identity teacherId,
            Identity subjectId,
            Identity courseId,
            Identity studentId) {
        CriteriaQuery<AssignmentData> criteriaQuery = cb.createQuery(AssignmentData.class);
        Root<AssignmentData> assignmentDataRoot = criteriaQuery.from(AssignmentData.class);

        Predicate predicate = getPredicate(
                title,
                teacherId,
                subjectId,
                courseId,
                assignmentDataRoot
        );

        criteriaQuery.where(predicate);

        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(
                assignmentDataRoot.get("updatedAt")
        ));
        criteriaQuery.orderBy(orders);
        criteriaQuery.select(assignmentDataRoot).distinct(true);



        TypedQuery<AssignmentData> assignmentQuery = em.createQuery(criteriaQuery);
        return assignmentQuery.getResultList();
    }

    private Predicate getPredicate(
            String title,
            Identity teacherId,
            Identity subjectId,
            Identity courseId,
            Root<AssignmentData> assignmentDataRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(title)) {
            predicates.add(cb.like(assignmentDataRoot.get("title"), "%" + title + "%"));
        }

        if (Objects.nonNull(teacherId)) {
            predicates.add(cb.equal(assignmentDataRoot.get("teacherId"), teacherId.getId()));
        }

        if (Objects.nonNull(subjectId)) {
            predicates.add(cb.equal(assignmentDataRoot.get("subjectId"), subjectId.getId()));
        }

        if (Objects.nonNull(courseId)) {
            predicates.add(cb.equal(assignmentDataRoot.get("courseId"), courseId.getId()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
