package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.domain.entities.QuestionAnswer;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="question_answer")
@Getter
@Setter
@Table(
        name="question_answer",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "assignment_attempt_id", "question_id" }) }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class QuestionAnswerData extends BaseEntity{
    @Column(name="text_answer", columnDefinition = "TEXT")
    private String textAnswer;
    @Column(name="teacher_fixed_text_answer", columnDefinition = "TEXT")
    private String teacherFixedTextAnswer;
    private Integer score;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;
    @ManyToOne
    @JoinColumn(name="creator_id")
    private UserData creator;
    @ManyToMany
    @JoinTable(
            name= "question_answer_question_choices",
            joinColumns = @JoinColumn(name= "question_answer_id"),
            inverseJoinColumns = @JoinColumn(name= "question_choice_id")
    )
    private Set<QuestionChoiceData> selectedOptions = new HashSet<>();
    @OneToMany(mappedBy = "answer")
    private List<QuestionAnswerFeedbackData> feedbacks;
    @ManyToOne
    @JoinColumn(name="assignment_attempt_id")
    private AssignmentAttemptData assignmentAttempt;

    public static QuestionAnswerData from(QuestionAnswer qa) {
        QuestionAnswerData result = QuestionAnswerData
                .builder()
                .textAnswer(qa.getTextAnswer())
                .teacherFixedTextAnswer(qa.getTeacherFixedTextAnswer())
                .score(qa.getScore())
                .question(Objects.nonNull(qa.getQuestion()) ? QuestionData.from(qa.getQuestion()) : null)
                .creator(Objects.nonNull(UserData.from(qa.getCreator())) ? UserData.from(qa.getCreator()) : null)
                .build();

        if (Objects.nonNull(qa.getSelectedOptions())) {
            result.setSelectedOptions(qa.getSelectedOptions().stream().map(QuestionChoiceData::from).collect(Collectors.toSet()));
        }

        if (Objects.nonNull(qa.getFeedbacks())) {
            result.setFeedbacks(qa.getFeedbacks().stream().map(QuestionAnswerFeedbackData::from).toList());
        }

        if (Objects.nonNull(qa.getId())) {
            result.setId(qa.getId().getId());
        }

        if (Objects.nonNull(qa.getAssignmentAttempt())) {
            result.setAssignmentAttempt(AssignmentAttemptData.from(qa.getAssignmentAttempt()));
        }

        result.setCreatedAt(qa.getCreatedAt());
        result.setUpdatedAt(qa.getUpdatedAt());
        result.setDeletedAt(qa.getDeletedAt());
        return result;
    }

    public static QuestionAnswerData from(Identity id) {
        QuestionAnswerData res = QuestionAnswerData
                .builder()
                .build();
        res.setId(id.getId());
        return res;
    }
    public QuestionAnswer fromThis() {
        QuestionAnswer result = QuestionAnswer
                .builder()
                .id(Identity.from(this.getId()))
                .textAnswer(this.textAnswer)
                .teacherFixedTextAnswer(this.teacherFixedTextAnswer)
                .score(this.score)
                .creator(this.creator.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getSelectedOptions())) {
            result.setSelectedOptions(this.selectedOptions.stream().map(QuestionChoiceData::fromThis).collect(Collectors.toSet()));
        }

        if (Objects.nonNull(this.getFeedbacks())) {
            result.setFeedbacks(this.feedbacks.stream().map(QuestionAnswerFeedbackData::fromThis).toList());
        }

        if (Objects.nonNull(this.getQuestion())) {
            result.setQuestion(Question.builder().id(Identity.from(this.getQuestion().getId())).build());
        }

        if (Objects.nonNull(this.getAssignmentAttempt())) {
            result.setAssignmentAttempt(AssignmentAttempt.builder().id(Identity.from(this.assignmentAttempt.getId())).build());
        }

        return result;
    }
}
