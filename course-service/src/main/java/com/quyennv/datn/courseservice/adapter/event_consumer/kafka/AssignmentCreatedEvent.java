package com.quyennv.datn.courseservice.adapter.event_consumer.kafka;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentCreatedEvent {
    String assignmentId;
    String lessonId;
    String title;
}
