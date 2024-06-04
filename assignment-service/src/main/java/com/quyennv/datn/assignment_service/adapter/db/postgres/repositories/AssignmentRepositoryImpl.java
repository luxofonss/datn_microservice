package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.AssignmentAttemptData;
import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.AssignmentRepository;
import com.quyennv.lms.adapter.jpa.entities.AssignmentData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AssignmentRepositoryImpl implements AssignmentRepository {
    private final  JpaAssignmentRepository jpaAssignmentRepository;
    private final CriteriaAssignmentRepository criteriaAssignmentRepository;

    public AssignmentRepositoryImpl(
            JpaAssignmentRepository jpaAssignmentRepository,
            CriteriaAssignmentRepository criteriaAssignmentRepository) {
        this.jpaAssignmentRepository = jpaAssignmentRepository;
        this.criteriaAssignmentRepository = criteriaAssignmentRepository;
    }

    @Override
    @Transactional
    public Assignment persist(Assignment assignment) {
        return jpaAssignmentRepository.save(AssignmentData.from(assignment)).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Assignment> findById(Identity id) {
        return jpaAssignmentRepository.findById(id.getId()).map(AssignmentData::fromThis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Assignment> findAllWithFilter(
            String title,
            Identity teacherId,
            Identity subjectId,
            Identity courseId,
            List<Identity> sectionIds,
            Identity studentId) {
        return criteriaAssignmentRepository
                .findAllWithFilter(title, teacherId, subjectId, courseId, studentId)
                .stream()
                .map(AssignmentData::info)
                .toList();
    }
}
