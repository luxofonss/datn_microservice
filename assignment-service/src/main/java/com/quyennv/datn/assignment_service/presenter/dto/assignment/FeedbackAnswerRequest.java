package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import com.quyennv.datn.assignment_service.core.domain.enums.QuestionAnswerFeedbackType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackAnswerRequest {
//    @NotBlank
    String id;
    String message;
    QuestionAnswerFeedbackType type;
}
