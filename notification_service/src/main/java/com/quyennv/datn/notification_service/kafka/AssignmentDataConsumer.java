package com.quyennv.datn.notification_service.kafka;

import com.quyennv.datn.notification_service.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AssignmentDataConsumer {

    private final NotificationService notificationService;

    public AssignmentDataConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener (
            topics = "notification_created",
            groupId = "notification-group",
            containerFactory = "notificationKafkaListenerContainerFactory")
    public void assignmentCreatedEventListener(NotificationCreatedEvent message) {
        log.info("Received message: {}", message);
        notificationService.insert(message.eventToNotification());
    }
}
