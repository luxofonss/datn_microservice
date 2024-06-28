package com.quyennv.datn.communication_service.core.domain.event;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
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
}
