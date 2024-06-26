package com.quyennv.datn.communication_service.presenter.config;

import com.quyennv.datn.communication_service.core.usecases.EventPublisher;
import com.quyennv.datn.communication_service.core.usecases.comment.CommentRepository;
import com.quyennv.datn.communication_service.core.usecases.comment.CreateCommentUseCase;
import com.quyennv.datn.communication_service.core.usecases.comment.DeleteCommentUseCase;
import com.quyennv.datn.communication_service.core.usecases.comment.UpdateCommentInfoUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.*;
import com.quyennv.datn.communication_service.core.usecases.notification.ConversationCreatedNotificationUseCase;
import com.quyennv.datn.communication_service.core.usecases.notification.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    CreateConversationUseCase createConversationUseCase(ConversationRepository conversationRepository,
                                                        ConversationCreatedNotificationUseCase conversationCreatedNotificationUseCase) {
        return new CreateConversationUseCase(conversationRepository, conversationCreatedNotificationUseCase);
    }

    @Bean
    UpdateConversationInfoUseCase updateConversationUseCase(ConversationRepository conversationRepository) {
        return new UpdateConversationInfoUseCase(conversationRepository);
    }

    @Bean
    DeleteConversationUseCase deleteConversationUseCase(ConversationRepository conversationRepository) {
        return new DeleteConversationUseCase(conversationRepository);
    }

    @Bean
    CreateCommentUseCase createCommentUseCase(CommentRepository commentRepository) {
        return new CreateCommentUseCase(commentRepository);
    }

    @Bean
    UpdateCommentInfoUseCase updateCommentInfoUseCase(CommentRepository commentRepository) {
        return new UpdateCommentInfoUseCase(commentRepository);
    }

    @Bean
    DeleteCommentUseCase deleteCommentUseCase(CommentRepository commentRepository) {
        return new DeleteCommentUseCase(commentRepository);
    }

    @Bean
    GetConversationsByParentUseCase getConversationsByParentUseCase(ConversationRepository conversationRepository) {
        return new GetConversationsByParentUseCase(conversationRepository);
    }

    @Bean
    ConversationCreatedNotificationUseCase conversationCreatedNotificationUseCase(CourseService courseService, EventPublisher eventPublisher) {
        return new ConversationCreatedNotificationUseCase(courseService, eventPublisher);
    }
}
