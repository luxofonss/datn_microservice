package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.LearnerInfo;
import com.quyennv.datn.courseservice.core.domain.enums.LearnerType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="learner_info")
@Getter
@Setter
@Table(name="learner_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearnerInfoData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private LearnerType type;
    private Integer grade;
    private String school;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    public static LearnerInfoData from(LearnerInfo learner) {
        LearnerInfoData res = LearnerInfoData.builder()
                .type(learner.getType())
                .grade(learner.getGrade())
                .school(learner.getSchool())
                .build();

        res.setId(learner.getId().getId());
        res.setCreatedAt(learner.getCreatedAt());
        res.setUpdatedAt(learner.getUpdatedAt());
        res.setDeletedAt(learner.getDeletedAt());

        return res;
    }

    public LearnerInfo fromThis() {
        return LearnerInfo.builder()
                .id(Identity.from(this.getId()))
                .userId(Identity.from(this.userId))
                .type(this.type)
                .grade(this.grade)
                .school(this.school)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
