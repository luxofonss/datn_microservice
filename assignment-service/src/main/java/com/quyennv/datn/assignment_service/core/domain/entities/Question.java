package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.QuestionLevel;
import com.quyennv.lms.core.domain.enums.QuestionType;
import com.quyennv.lms.core.utils.FunctionHelper;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Question {
    private Identity id;

    private QuestionLevel level;
    private String title;
    private String image;
    private String audio;
    private Integer mark;
    private QuestionType type;
    private String answerExplanation;
    private List<QuestionChoice> choices;
    private List<QuestionTextAnswer> textAnswers;
    private QuestionAnswer answer;
    private Assignment assignment;
    private User creator;
    private Subject subject;
    private Question question;
    private List<Question> subQuestions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Question update(Question updatedQuestion) {
        BeanUtils.copyProperties(updatedQuestion, this, FunctionHelper.getNullPropertyNames(updatedQuestion));

        if (updatedQuestion.getChoices() != null) {
            this.choices.forEach(c -> {
                updatedQuestion.getChoices().forEach(updatedChoice -> {
                    if (c.getId().equals(updatedChoice.getId())) {
                        c.update(updatedChoice);
                    }
                });
            });
            this.setChoices(updatedQuestion.getChoices());
        }

        if (Objects.nonNull(updatedQuestion.getSubQuestions())) {
            this.subQuestions.forEach(q -> {
                updatedQuestion.getSubQuestions().forEach(question -> {
                    if (q.getId().equals(question.getId())) {
                        q.update(question);
                    }
                });
            });
            this.setSubQuestions(updatedQuestion.getSubQuestions());
        }

        if (updatedQuestion.getTextAnswers() != null) {
            this.textAnswers.forEach(ta -> {
                updatedQuestion.getTextAnswers().forEach(updatedTextAnswer -> {
                    if (ta.getId().equals(updatedTextAnswer.getId())) {
                        ta.update(updatedTextAnswer);
                    }
                });
            });
            this.setTextAnswers(updatedQuestion.getTextAnswers());
        }

        return this;
    }
}