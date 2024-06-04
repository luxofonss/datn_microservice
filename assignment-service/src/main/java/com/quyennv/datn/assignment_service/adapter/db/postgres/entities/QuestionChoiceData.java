package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionChoice;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="question_choices")
@Getter
@Setter
@ToString(exclude = {"question", "chosenAnswers"})
@Table(name="question_choices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class QuestionChoiceData extends BaseEntity {
    private String content;
    @Column(name="ord")
    private Integer order;
    @Column(name="is_correct")
    private Boolean isCorrect;
    private String explanation;
    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;

    @ManyToMany(mappedBy = "selectedOptions")
    private Set<QuestionAnswerData> chosenAnswers;

    public static QuestionChoiceData from (QuestionChoice c) {
        QuestionChoiceData result = QuestionChoiceData
                .builder()
                .content(c.getContent())
                .order(c.getOrder())
                .isCorrect(c.getIsCorrect())
                .explanation(c.getExplanation())
                .question(Objects.nonNull(c.getQuestion()) ? QuestionData.from(c.getQuestion()) : null)
                .build();

        if (Objects.nonNull(c.getId())) {
            result.setId(c.getId().getId());
        }


        if (Objects.nonNull(c.getChosenAnswers())) {
            result.setChosenAnswers(c.getChosenAnswers().stream().map(QuestionAnswerData::from).collect(Collectors.toSet()));
        }

        return result;
    }

    public QuestionChoice fromThis() {

        return QuestionChoice
                .builder()
                .id(Identity.from(this.getId()))
                .order(this.order)
                .isCorrect(this.isCorrect)
                .content(this.content)
                .explanation(this.explanation)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}