package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionTextAnswer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name="question_text_answers")
@Getter
@Setter
@Table(name="question_text_answers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionTextAnswerData extends BaseEntity{
    private String answer;
    private String explanation;
    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;

    public static QuestionTextAnswerData from(QuestionTextAnswer a) {
        QuestionTextAnswerData result = QuestionTextAnswerData
                .builder()
                .answer(a.getAnswer())
                .explanation(a.getExplanation())
                .question(Objects.nonNull(a.getQuestion())? QuestionData.from(a.getQuestion()) : null)
                .build();

        if(Objects.nonNull(a.getId())) {
            result.setId(a.getId().getId());
        }
        result.setCreatedAt(a.getCreatedAt());
        result.setUpdatedAt(a.getUpdatedAt());
        result.setDeletedAt(a.getDeletedAt());

        return result;
    }

    public QuestionTextAnswer fromThis() {
        return QuestionTextAnswer
                .builder()
                .id(Identity.from(this.getId()))
                .answer(this.answer)
                .explanation(this.explanation)
//                .question(this.question.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
