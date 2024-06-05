package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import lombok.Value;

import java.util.List;

@Value
public class SubmitQuestionAnswerRequest {
    String textAnswer;
    List<String> selectedOptionIds;
}
