package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.Comment;
import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name="user_id")
    UserData user;

    @ManyToOne
    @JoinColumn(name="conversation_id")
    ConversationData conversation;

    @ManyToOne
    @JoinColumn(name="lesson_id")
    LessonData lesson;

    public static CommentData from(Comment comment) {
        CommentData result = CommentData
                .builder()
                .content(comment.getContent())
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

        if (Objects.nonNull(comment.getLesson())) {
            result.setLesson(LessonData.from(comment.getLesson()));
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
                .user(this.getUser().fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
