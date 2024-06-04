package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.utils.FunctionHelper;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"question", "chosenAnswers"})
public class QuestionChoice {
    private Identity id;
    private Integer order;
    private Boolean isCorrect;
    private String content;
    private String explanation;
    private Question question;

    private List<QuestionAnswer> chosenAnswers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public QuestionChoice update(QuestionChoice updatedChoice) {
        BeanUtils.copyProperties(updatedChoice, this, FunctionHelper.getNullPropertyNames(updatedChoice));

        return this;
    }
}
