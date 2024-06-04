package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.enums.QuestionAnswerFeedbackType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackAnswerRequest {
    @NotBlank
    String id;
    String message;
    QuestionAnswerFeedbackType type;
}
