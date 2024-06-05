package com.quyennv.datn.assignment_service.core.repositories;


import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;

import java.util.Optional;

public interface QuestionAnswerFeedbackRepository {
    QuestionAnswerFeedback persist(QuestionAnswerFeedback feedback);
    QuestionAnswerFeedback delete(QuestionAnswerFeedback feedback);
    Optional<QuestionAnswerFeedback> findById(Identity id);
}
