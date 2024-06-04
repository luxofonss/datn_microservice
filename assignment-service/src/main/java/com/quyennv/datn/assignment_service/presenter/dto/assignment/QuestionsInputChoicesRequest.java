package com.quyennv.lms.presenter.rest.dto.assignment;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class QuestionsInputChoicesRequest {
    String id;
    @NotBlank
    String content;
    Integer order;
    Boolean isCorrect;
    String explanation;
}
