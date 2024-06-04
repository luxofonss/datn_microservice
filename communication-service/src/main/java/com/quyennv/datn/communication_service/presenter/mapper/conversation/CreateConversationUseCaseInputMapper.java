package com.quyennv.lms.presenter.rest.mapper.conversation;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.ConversationType;
import com.quyennv.lms.core.usecases.conversation.CreateConversationUseCase;
import com.quyennv.lms.presenter.rest.dto.CreateConversationRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class CreateConversationUseCaseInputMapper {
    public static CreateConversationUseCase.InputValues map(CreateConversationRequest request, UserPrincipal requester) {
        return CreateConversationUseCase.InputValues.builder()
                .content(request.getContent())
                .type(request.getType() != null ? ConversationType.valueOf(request.getType()) : null)
                .lessonId(request.getLessonId() != null ? Identity.from(request.getLessonId()) : null)
                .userId(Identity.from(requester.getId()))
                .courseId(request.getCourseId() != null ? Identity.from(request.getCourseId()) : null)
                .build();
    }
}
