package com.quyennv.datn.communication_service.core.usecases.notification;

import com.quyennv.datn.communication_service.core.constants.Constant;
import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.User;
import com.quyennv.datn.communication_service.core.domain.enums.NotificationPlacementType;
import com.quyennv.datn.communication_service.core.domain.enums.NotificationTypeEnum;
import com.quyennv.datn.communication_service.core.domain.event.NotificationCreatedEvent;
import com.quyennv.datn.communication_service.core.domain.valueobject.Course;
import com.quyennv.datn.communication_service.core.usecases.EventPublisher;
import com.quyennv.datn.communication_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ConversationCreatedNotificationUseCase extends UseCase<ConversationCreatedNotificationUseCase.InputValues,
        ConversationCreatedNotificationUseCase.OutputValues> {
    
    private final CourseService courseService;
    
    private final EventPublisher eventPublisher;

    public ConversationCreatedNotificationUseCase(CourseService courseService, EventPublisher eventPublisher) {
        this.courseService = courseService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = courseService.getCourseById(input.courseId);
        
        if (Objects.isNull(course)) {
            return null;
        }

        List<NotificationCreatedEvent> events = buildNotificationCreatedEvent(input, course);

        events.forEach(event -> {
            eventPublisher.publish(event, Constant.NOTIFICATION_CREATED_TOPIC);
        });
        
        return null;
    }

    private List<NotificationCreatedEvent> buildNotificationCreatedEvent(InputValues input, Course course) {
        // not created by teacher
        if (course.getTeacher().getId() == input.conversation.getUser().getId()) {
            NotificationCreatedEvent event = NotificationCreatedEvent.builder()
                    .studentId(input.studentId)
                    .teacherId(course.getTeacher().getId().toString())
                    .destinationId(course.getTeacher().getId().toString())
                    .courseId(input.courseId)
                    .lessonId(input.lessonId)
                    .conversationId(input.conversation.getId().toString())
                    .isSeen(false)
                    .type(NotificationTypeEnum.CONVERSATION_CREATED.getValue())
                    .createdAt(ZonedDateTime.now().toString())
                    .updatedAt(ZonedDateTime.now().toString())
                    .build();

            event.setMessage("You have a new conversation in your course "
                    + course.getName()
                    + " with "
                    + input.conversation.getUser().getFirstName() + " " + input.conversation.getUser().getLastName());

            if (input.lessonId != null) {
                event.setPlacementType(NotificationPlacementType.LESSON.getValue());
            } else {
                event.setPlacementType(NotificationPlacementType.COURSE.getValue());
            }

            return Collections.singletonList(event);
        } else {
            List<Course.CourseStudent> students = course.getStudents();

            return students.stream().map(student -> {
                NotificationCreatedEvent event = NotificationCreatedEvent.builder()
                        .studentId(student.getStudent().getId().toString())
                        .teacherId(course.getTeacher().getId().toString())
                        .destinationId(input.conversation.getUser().getId().toString())
                        .courseId(input.courseId)
                        .lessonId(input.lessonId)
                        .conversationId(input.conversation.getId().toString())
                        .isSeen(false)
                        .type(NotificationTypeEnum.CONVERSATION_CREATED.getValue())
                        .createdAt(ZonedDateTime.now().toString())
                        .updatedAt(ZonedDateTime.now().toString())
                        .build();
                event.setMessage("Your teacher has started a new conversation in "
                        + course.getName()
                        + " with "
                        + input.conversation.getUser().getFirstName() + " " + input.conversation.getUser().getLastName());

                if (input.lessonId != null) {
                    event.setPlacementType(NotificationPlacementType.LESSON.getValue());
                } else {
                    event.setPlacementType(NotificationPlacementType.COURSE.getValue());
                }

                return event;
            }).toList();
        }
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String studentId;
        String courseId;
        String lessonId;
        Conversation conversation;
    }

    public static class OutputValues implements UseCase.OutputValues {
        String type;
    }
}
