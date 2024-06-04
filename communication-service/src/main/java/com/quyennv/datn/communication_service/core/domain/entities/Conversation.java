package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.ConversationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Conversation {
    Identity id;
    ConversationType type;
    String content;
    User user;
    Course course;
    Lesson lesson;
    List<Comment> comments;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    public Conversation update(Conversation conversation) {
        this.setContent(conversation.getContent());
        this.setUpdatedAt(LocalDateTime.now());
        return this;
    }

    public Conversation delete() {
        this.setDeletedAt(LocalDateTime.now());
        return this;
    }
}
