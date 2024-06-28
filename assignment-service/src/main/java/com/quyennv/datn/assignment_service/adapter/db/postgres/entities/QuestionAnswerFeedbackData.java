package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.datn.assignment_service.core.domain.enums.QuestionAnswerFeedbackType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="question_answer_feedbacks")
@Getter
@Setter
@Table(name="question_answer_feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class QuestionAnswerFeedbackData{
    @Id
    private UUID id;

    private String message;

    @Enumerated(EnumType.STRING)
    private QuestionAnswerFeedbackType type;

    @JoinColumn(name="creator_id")
    @ManyToOne
    private UserData creator;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionAnswerData answer;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static QuestionAnswerFeedbackData from (QuestionAnswerFeedback f) {
        QuestionAnswerFeedbackData result = QuestionAnswerFeedbackData
                .builder()
                .message(f.getMessage())
                .type(f.getType())
                .creator(UserData.builder().id(f.getCreatorId().getUUID()).build())
                .answer(f.getAnswer() != null ? QuestionAnswerData.from(f.getAnswer().getId()) : null)
                .build();

        result.setId(f.getId().getId());
        result.setCreatedAt(f.getCreatedAt());
        result.setUpdatedAt(f.getUpdatedAt());
        result.setDeletedAt(f.getDeletedAt());

        log.info("result:: {}",result.getId());

        return result;
    }

    public QuestionAnswerFeedback fromThis() {
        return QuestionAnswerFeedback
                .builder()
                .id(Identity.from(this.getId()))
                .creatorId(Identity.from(this.creator.getId()))
                .creator(this.creator.fromThis())
                .message(this.message)
                .type(this.type)
//                .answer(this.answer.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
