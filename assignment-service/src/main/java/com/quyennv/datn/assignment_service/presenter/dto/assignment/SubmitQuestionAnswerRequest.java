package com.quyennv.lms.presenter.rest.dto.assignment;

import lombok.Value;

import java.util.List;

@Value
public class SubmitQuestionAnswerRequest {
    String textAnswer;
    List<String> selectedOptionIds;
}
