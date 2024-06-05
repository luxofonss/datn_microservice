package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionLevel;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionType;
import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionLevel;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="questions")
@Getter
@Setter
@ToString(exclude = {"creator", "assignment", "subject", "choices", "textAnswers", "question", "subQuestions"})
@Table(name="questions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class QuestionData extends BaseEntity{
    private String title;
    private String image;
    private String audio;
    private Integer mark;
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @Column(name="answer_explaination")
    private String answerExplanation;

    @OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionChoiceData> choices;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionTextAnswerData> textAnswers;

    @Column(name="subject_id")
    private UUID subjectId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="assignment_id")
    private AssignmentData assignment;

    @Column(name="creatror_id")
    private UUID creatorId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;

    @OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionData> subQuestions;

    public static QuestionData withId(UUID id) {
        QuestionData q = QuestionData.builder().build();
        q.setId(id);
        return q;
    }

    public static QuestionData from(Question q) {
        QuestionData result = QuestionData
                .builder()
                .level(q.getLevel())
                .title(q.getTitle())
                .image(q.getImage())
                .audio(q.getAudio())
                .mark(q.getMark())
                .type(q.getType())
                .answerExplanation(q.getAnswerExplanation())
                .creatorId(Objects.nonNull(q.getCreatorId()) ? q.getCreatorId().getId() : null)
                .subjectId(Objects.nonNull(q.getSubjectId()) ? q.getSubjectId().getId() : null)
                .build();

        if (Objects.nonNull(q.getId())) {
            result.setId(q.getId().getId());
        }

        if (Objects.nonNull(q.getAssignment())) {
            result.setAssignment(AssignmentData.from(q.getAssignment()));
        }

        if (Objects.nonNull(q.getChoices())) {
            List<QuestionChoiceData> choices = q.getChoices().stream().map(
                    c -> {
                        QuestionChoiceData choice = QuestionChoiceData.from(c);
                        choice.setQuestion(result);
                        return choice;
                    }
            ).toList();
            result.setChoices(choices);
        }

        if (Objects.nonNull(q.getTextAnswers())) {
            List<QuestionTextAnswerData> textAnswers = q.getTextAnswers().stream().map(
                    t -> {
                        QuestionTextAnswerData textAnswer = QuestionTextAnswerData.from(t);
                        textAnswer.setQuestion(result);
                        return textAnswer;
                    }
            ).toList();
            result.setTextAnswers(textAnswers);
        }

        if (Objects.nonNull(q.getSubQuestions())) {
            List<QuestionData> questions = q.getSubQuestions().stream().map(
                    qu -> {
                        QuestionData question = QuestionData.from(qu);
                        question.setQuestion(result);
                        return question;
                    }
            ).toList();
            result.setSubQuestions(questions);
        }

        if (Objects.nonNull(q.getQuestion())) {
            result.setQuestion(QuestionData.withId(q.getQuestion().getId().getId()));
        }

         return result;
    }

    public Question fromThis() {
        Question result = Question
                .builder()
                .id(Identity.from(this.getId()))
                .level(this.level)
                .title(this.title)
                .image(this.image)
                .audio(this.audio)
                .mark(this.mark)
                .type(this.type)
                .answerExplanation(this.answerExplanation)
                .creatorId(Identity.from(this.creatorId))
                .subjectId(Identity.from(this.subjectId))
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.choices)) {
            result.setChoices(this.choices.stream().map(QuestionChoiceData::fromThis).toList());
        }

        if (Objects.nonNull(this.textAnswers)) {
            result.setTextAnswers(this.textAnswers.stream().map(QuestionTextAnswerData::fromThis).toList());
        }

        if (Objects.nonNull(this.subQuestions)) {
            result.setSubQuestions(this.subQuestions.stream().map(QuestionData::fromThis).toList());
        }

        return result;
    }
}
