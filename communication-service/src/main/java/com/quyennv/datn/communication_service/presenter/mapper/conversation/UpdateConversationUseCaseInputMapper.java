package com.quyennv.lms.presenter.rest.mapper.conversation;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.ConversationType;
import com.quyennv.lms.core.usecases.conversation.UpdateConversationInfoUseCase;
import com.quyennv.lms.presenter.rest.dto.UpdateConversationRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class UpdateConversationUseCaseInputMapper {
    public static UpdateConversationInfoUseCase.InputValues map(String id, UpdateConversationRequest request, UserPrincipal requester) {
        return UpdateConversationInfoUseCase.InputValues.builder()
                .id(Identity.fromString(id))
                .content(request.getContent())
                .type(request.getType() != null ? ConversationType.valueOf(request.getType()) : null)
                .lessonId(request.getLessonId() != null ? Identity.from(request.getLessonId()) : null)
                .userId(Identity.from(requester.getId()))
                .courseId(request.getCourseId() != null ? Identity.from(request.getCourseId()) : null)
                .build();
    }
}
