package com.quyennv.datn.communication_service.presenter.config;

import com.quyennv.datn.communication_service.core.usecases.comment.CommentRepository;
import com.quyennv.datn.communication_service.core.usecases.comment.CreateCommentUseCase;
import com.quyennv.datn.communication_service.core.usecases.comment.DeleteCommentUseCase;
import com.quyennv.datn.communication_service.core.usecases.comment.UpdateCommentInfoUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.ConversationRepository;
import com.quyennv.datn.communication_service.core.usecases.conversation.CreateConversationUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.DeleteConversationUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.UpdateConversationInfoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    CreateConversationUseCase createConversationUseCase(ConversationRepository conversationRepository) {
        return new CreateConversationUseCase(conversationRepository);
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
}
