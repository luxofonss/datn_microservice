package com.quyennv.lms.core.usecases.assignment;


import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;

import java.util.Optional;

public interface QuestionAnswerFeedbackRepository {
    QuestionAnswerFeedback persist(QuestionAnswerFeedback feedback);
    QuestionAnswerFeedback delete(QuestionAnswerFeedback feedback);
    Optional<QuestionAnswerFeedback> findById(Identity id);
}
