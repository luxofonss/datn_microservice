package com.quyennv.datn.courseservice.adapter.event_consumer.kafka;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.usecases.lesson.UpdateLessonAssigmentUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AssignmentDataConsumer {
    private final UpdateLessonAssigmentUseCase updateLessonAssigmentUseCase;

    public AssignmentDataConsumer(UpdateLessonAssigmentUseCase updateLessonAssigmentUseCase) {
        this.updateLessonAssigmentUseCase = updateLessonAssigmentUseCase;
    }

    @KafkaListener (
            topics = "assignment_created",
            groupId = "assignments-group",
            containerFactory = "assignmentKafkaListenerContainerFactory")
    public void assignmentCreatedEventListener(AssignmentCreatedEvent message) {
        log.info("Received message: {}", message);
        UpdateLessonAssigmentUseCase.InputValues inputValues = new UpdateLessonAssigmentUseCase.InputValues(
                Identity.fromString(message.getLessonId()),
                Identity.fromString(message.getAssignmentId())
        );
        UpdateLessonAssigmentUseCase.OutputValues outputValues = updateLessonAssigmentUseCase.execute(inputValues);
        log.info("Update successfully lesson: {}", outputValues);
    }
}
