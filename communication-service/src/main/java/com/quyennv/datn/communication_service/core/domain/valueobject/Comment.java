package com.quyennv.datn.communication_service.core.domain.valueobject;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.domain.entities.User;
import com.quyennv.datn.communication_service.core.domain.valueobject.Conversation;
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
}
