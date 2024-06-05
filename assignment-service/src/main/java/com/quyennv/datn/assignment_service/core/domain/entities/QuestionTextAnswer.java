package com.quyennv.datn.assignment_service.core.domain.entities;

import com.quyennv.datn.assignment_service.core.utils.FunctionHelper;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"question"})
@Builder
public class QuestionTextAnswer {
    Identity id;
    private String answer;
    private String explanation;

    private Question question;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public QuestionTextAnswer update(QuestionTextAnswer updatedTextAnswer) {
        BeanUtils.copyProperties(updatedTextAnswer, this, FunctionHelper.getNullPropertyNames(updatedTextAnswer));

        return this;
    }
}
