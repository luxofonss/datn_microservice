package com.quyennv.datn.communication_service.adapter.db.postgres.entities;

import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="conversations")
@Table(name = "conversations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"comments"})
@Slf4j
public class ConversationData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private ConversationType type;

    private String content;

//    @Column(name="user_id")
//    UUID userId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserData user;

    @Column(name="target_placement_id")
    UUID targetPlacementId;


    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<CommentData> comments;

    public static ConversationData from(Conversation conversation) {
        ConversationData result = ConversationData
                .builder()
                .type(conversation.getType())
                .content(conversation.getContent())
                .targetPlacementId(Objects.nonNull(conversation.getTargetPlacementId())
                        ? conversation.getTargetPlacementId().getUUID()
                        : null)
                .build();

        if (Objects.nonNull(conversation.getId())) {
            result.setId(conversation.getId().getId());
        }
        if (Objects.nonNull(conversation.getUser())) {
            result.setUser(UserData.from(conversation.getUser()));
        }
        result.setCreatedAt(conversation.getCreatedAt());
        result.setUpdatedAt(conversation.getUpdatedAt());
        result.setDeletedAt(conversation.getDeletedAt());

        return result;
    }

    public Conversation fromThis() {
        log.info("this:: {}", this);
        Conversation result = Conversation
                .builder()
                .type(this.getType())
                .content(this.getContent())
                .targetPlacementId(Identity.from(this.targetPlacementId))
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getId())) {
            result.setId(Identity.from(this.getId()));
        }

        if (Objects.nonNull(this.getUser())) {
            result.setUser(this.getUser().fromThis());
        }
        if (Objects.nonNull(this.getComments())) {
            result.setComments(this.comments.stream().map(CommentData::fromThis).toList());
        }
        return result;
    }
}
