package com.quyennv.datn.assignment_service.adapter.db.postgres.entities;

import com.quyennv.datn.assignment_service.core.domain.enums.QuestionLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="question_placement")
@Getter
@Setter
@Table(name="question_placement")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionPlacementData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;
    @Column(name="ord")
    private Integer order;
    private Integer mark;

    @ManyToOne
    @JoinColumn(name="question_id", nullable = false)
    private QuestionData question;

    @ManyToOne
    @JoinColumn(name="assignment_id", nullable = false)
    private AssignmentData assignment;

    @Column(name="creator_id")
    private UUID creatorId;
}
