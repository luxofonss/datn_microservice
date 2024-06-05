package com.quyennv.datn.communication_service.core.domain.entities;

import com.quyennv.datn.communication_service.core.domain.valueobject.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Comment {
    Identity id;
    String content;
    User user;
    Conversation conversation;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    public Comment update(Comment comment) {
        this.setContent(comment.getContent());
        this.setUpdatedAt(LocalDateTime.now());
        return this;
    }

    public Comment delete() {
        this.setDeletedAt(LocalDateTime.now());
        return this;
    }
}
