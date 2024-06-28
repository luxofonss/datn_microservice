package com.quyennv.datn.assignment_service.adapter.event_publisher.kafka;

import com.quyennv.datn.assignment_service.adapter.event_publisher.repository.KafkaAssignmentAttemptRepository;
import com.quyennv.datn.assignment_service.adapter.event_publisher.repository.KafkaAssignmentRepository;
import com.quyennv.datn.assignment_service.core.constants.Constant;
import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.AssignmentAttempt;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.domain.enums.NotificationTypeEnum;
import com.quyennv.datn.assignment_service.core.domain.event.AssignmentCreatedEvent;
import com.quyennv.datn.assignment_service.core.domain.event.NotificationCreatedEvent;
import com.quyennv.datn.assignment_service.core.usecases.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaEventPublisher implements EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final KafkaAssignmentRepository kafkaAssignmentRepository;

    private final KafkaAssignmentAttemptRepository kafkaAssignmentAttemptRepository;

    public KafkaEventPublisher(
            KafkaTemplate<String,Object> kafkaTemplate,
            KafkaAssignmentRepository kafkaAssignmentRepository,
            KafkaAssignmentAttemptRepository kafkaAssignmentAttemptRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaAssignmentRepository = kafkaAssignmentRepository;
        this.kafkaAssignmentAttemptRepository = kafkaAssignmentAttemptRepository;
    }

    private void publish(Object event, String topic) {
        log.info("send event:: {}", event.toString());
        kafkaTemplate.send(topic, event);
    }

    @Override
    public void publishFeedbackCreatedNotificationEvent(QuestionAnswerFeedback feedback) {
        log.info("run publish event");
        CompletableFuture.runAsync(() -> {
            log.info("run publish event2");

            AssignmentAttempt assignmentAttempt =
                    kafkaAssignmentAttemptRepository.findByQuestionAnswerId(feedback.getAnswer().getId().getUUID()).orElseThrow();

            log.info("assignmentAttempt:: {}", assignmentAttempt.toString());
            Assignment assignment = kafkaAssignmentRepository.getAssignmentByAttemptId(assignmentAttempt.getId().getUUID()).orElseThrow();

            log.info("assignment:: {}", assignment.toString());

            NotificationCreatedEvent notificationCreatedEvent = NotificationCreatedEvent
                    .builder()
                    .type(NotificationTypeEnum.QUESTION_ANSWER_FEEDBACK.getValue())
                    .studentId(assignmentAttempt.getStudent().getId().toString())
                    .teacherId(feedback.getCreatorId().toString())
                    .destinationId(assignmentAttempt.getStudent().getId().toString())
                    .courseId(assignment.getCourseId().toString())
                    .lessonId(null)
                    .conversationId(null)
                    .assignmentAttemptId(assignmentAttempt.getId().toString())
                    .placementType(null)
                    .studentName(null)
                    .message(String.format("Bạn có một feedback mới trong bài tập %s", assignment.getTitle()))
                    .isSeen(false)
                    .createdAt(ZonedDateTime.now().toString())
                    .updatedAt(ZonedDateTime.now().toString())
                    .build();

            log.info("here");

            publish(notificationCreatedEvent, Constant.NOTIFICATION_CREATED_TOPIC);

            log.info("Published notification event");
        }).exceptionally(e -> {
            log.error("Error while publishing notification:: {}", e.getMessage());
            return null;
        });
    }

    @Override
    public void publishAssignmentCreatedEvent(Assignment assignment) {
        CompletableFuture.runAsync(() -> {
            AssignmentCreatedEvent event = new AssignmentCreatedEvent(
                    assignment.getId().getId().toString(),
                    assignment.getLessonId().getId().toString(),
                    assignment.getTitle()
            );
            publish(event, Constant.ASSIGNMENT_CREATED_TOPIC);

            log.info("Published assignment created event");
        }).exceptionally(e -> {
            log.error("Error while publishing assignment created event:: {}", e.getMessage());
            return null;
        });
    }
}
