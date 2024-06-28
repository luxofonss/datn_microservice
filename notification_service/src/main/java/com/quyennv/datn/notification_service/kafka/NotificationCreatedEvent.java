package com.quyennv.datn.notification_service.kafka;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.datn.notification_service.entity.Notification;
import com.quyennv.datn.notification_service.utils.TimeUtils;
import com.quyennv.datn.notification_service.utils.UuidUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificationCreatedEvent {
    String type;

    String studentId;

    String teacherId;

    String destinationId;

    String courseId;

    String lessonId;

    String conversationId;

    String assignmentAttemptId;

    String placementType;

    String studentName;

    String message;

    Boolean isSeen;

    String createdAt;

    String updatedAt;

    String deletedAt;

    public Notification eventToNotification() {
        return Notification
                .builder()
                .id(UUID.randomUUID())
                .type(this.getType())
                .studentId(UuidUtils.fromString(this.getStudentId()))
                .teacherId(UuidUtils.fromString(this.getTeacherId()))
                .destinationId(UuidUtils.fromString(this.getDestinationId()))
                .courseId(UuidUtils.fromString(this.getCourseId()))
                .lessonId(UuidUtils.fromString(this.getLessonId()))
                .conversationId(UuidUtils.fromString(this.getConversationId()))
                .assignmentAttemptId(UuidUtils.fromString(this.getAssignmentAttemptId()))
                .placementType(this.getPlacementType())
                .studentName(this.getStudentName())
                .message(this.getMessage())
                .isSeen(this.getIsSeen())
                .createdAt(new Timestamp(new Date().getTime()))
                .updatedAt(new Timestamp(new Date().getTime()))
                .build();
    }
}
