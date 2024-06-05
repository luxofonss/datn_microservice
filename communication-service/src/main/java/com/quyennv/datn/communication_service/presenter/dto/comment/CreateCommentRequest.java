package com.quyennv.datn.communication_service.presenter.dto.comment;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCommentRequest {
    String content;
    UUID conversationId;
}
