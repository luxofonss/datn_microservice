package com.quyennv.datn.communication_service.adapter.db.postgres.entities;

import com.quyennv.datn.communication_service.core.domain.entities.Comment;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;

@Entity(name="comments")
@Table(name="comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CommentData extends BaseEntity {
    String content;

//    @Column(name="user_id")
//    UUID userId;

    @ManyToOne
    @JoinColumn(name="user_id")
    UserData user;

    @ManyToOne
    @JoinColumn(name="conversation_id")
    ConversationData conversation;

    public static CommentData from(Comment comment) {
        CommentData result = CommentData
                .builder()
                .content(comment.getContent())
//                .userId(comment.getUser().getId().getUUID())
                .build();

        if (Objects.nonNull(comment.getId())) {
            result.setId(comment.getId().getId());
        }
        if (Objects.nonNull(comment.getUser())) {
            result.setUser(UserData.from(comment.getUser()));
        }
        if (Objects.nonNull(comment.getConversation())) {
            result.setConversation(ConversationData.from(comment.getConversation()));
        }

        result.setCreatedAt(comment.getCreatedAt());
        result.setUpdatedAt(comment.getUpdatedAt());
        result.setDeletedAt(comment.getDeletedAt());

        return result;
    }

    public Comment fromThis() {
        return Comment
                .builder()
                .id(Identity.from(this.getId()))
                .content(this.getContent())
//                .user(User.builder().id(Identity.from(this.getUserId())).build())
                .user(this.getUser().fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
