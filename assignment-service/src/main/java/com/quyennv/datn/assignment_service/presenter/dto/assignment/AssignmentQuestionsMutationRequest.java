package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import com.quyennv.datn.assignment_service.core.domain.enums.QuestionLevel;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionType;
import com.quyennv.datn.assignment_service.presenter.config.annotations.ValueOfEnum;
import lombok.Value;

import java.util.List;

@Value
public class AssignmentQuestionsMutationRequest {
    String id;
    String title;
    String description;
    String image;
    String audio;
    Integer mark;
    @ValueOfEnum(enumClass = QuestionLevel.class)
    String level;
    @ValueOfEnum(enumClass = QuestionType.class)
    String type;
    String answerExplanation;
    List<QuestionsInputChoicesRequest> choices;
    List<QuestionsInputTextAnswersRequest> textAnswers;
    List<AssignmentQuestionsMutationRequest> subQuestions;
}
