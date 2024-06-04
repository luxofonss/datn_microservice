package com.quyennv.lms.presenter.rest.dto;

import com.quyennv.lms.core.domain.enums.ConversationType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UpdateConversationRequest {
    private String content;
    @ValueOfEnum(enumClass = ConversationType.class)
    private String type;
    private UUID lessonId;
    private UUID courseId;
}
