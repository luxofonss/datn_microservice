package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import lombok.Value;

@Value
public class QuestionsInputTextAnswersRequest {
    String id;
    String answer;
    String explanation;
}
