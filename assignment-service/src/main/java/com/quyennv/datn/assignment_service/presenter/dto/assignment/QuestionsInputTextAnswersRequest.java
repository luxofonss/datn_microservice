package com.quyennv.lms.presenter.rest.dto.assignment;

import lombok.Value;

@Value
public class QuestionsInputTextAnswersRequest {
    String id;
    String answer;
    String explanation;
}
