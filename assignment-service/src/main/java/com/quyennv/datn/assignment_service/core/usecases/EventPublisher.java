package com.quyennv.datn.assignment_service.core.usecases;

import com.quyennv.datn.assignment_service.core.domain.entities.Assignment;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;

public interface EventPublisher {

    void publishFeedbackCreatedNotificationEvent(QuestionAnswerFeedback feedback);

    void publishAssignmentCreatedEvent(Assignment assignment);
}
