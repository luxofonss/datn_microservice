package com.quyennv.datn.communication_service.core.domain.entities;

import com.quyennv.datn.communication_service.core.domain.enums.ConversationType;
import com.quyennv.datn.communication_service.core.domain.valueobject.User;
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
    Identity targetPlacementId;
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
