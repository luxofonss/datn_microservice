package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.enums.QuestionLevel;
import com.quyennv.lms.core.domain.enums.QuestionType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
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
