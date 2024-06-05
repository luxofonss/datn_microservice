package com.quyennv.datn.assignment_service.presenter.dto.assignment;

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
