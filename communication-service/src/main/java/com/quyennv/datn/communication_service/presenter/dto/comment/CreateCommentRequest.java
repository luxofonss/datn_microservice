package com.quyennv.lms.presenter.rest.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCommentRequest {
    String content;
    UUID conversationId;
}
