package com.quyennv.datn.assignment_service.adapter.db.postgres.repositories;

import com.quyennv.datn.assignment_service.adapter.db.postgres.entities.AssignmentData;
import com.quyennv.datn.assignment_service.adapter.event_publisher.repository.KafkaAssignmentRepository;
import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.repositories.AssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class AssignmentRepositoryImpl implements AssignmentRepository, KafkaAssignmentRepository {
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
        jpaAssignmentRepository.deleteByLessonIdAndCourseId(assignment.getLessonId().getId(), assignment.getCourseId().getId());
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
                .map(AssignmentData::fromThis)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Assignment> getAssignmentByAttemptId(UUID assignmentAttemptId) {
        return jpaAssignmentRepository.findByAssignmentAttemptId(assignmentAttemptId).map(AssignmentData::fromThis);
    }
}
