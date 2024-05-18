package com.quyennv.datn.courseservice.core.domain.valueobject;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
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
    String type;
    String content;
    User user;
    List<Comment> comments;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
