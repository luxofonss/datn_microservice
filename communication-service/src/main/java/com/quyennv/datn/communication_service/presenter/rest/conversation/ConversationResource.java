package com.quyennv.datn.communication_service.presenter.rest.conversation;

import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import com.quyennv.datn.communication_service.presenter.dto.conversation.CreateConversationRequest;
import com.quyennv.datn.communication_service.presenter.dto.conversation.UpdateConversationRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/conversations")
public interface ConversationResource {
    @PostMapping
    CompletableFuture<ApiResponse> createConversation(
            @RequestBody @Valid CreateConversationRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );

    @PutMapping("/{id}")
    CompletableFuture<ApiResponse> updateConversation(
            @PathVariable @NotBlank String id,
            @RequestBody @Valid UpdateConversationRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );

    @DeleteMapping("/{id}")
    CompletableFuture<ApiResponse> deleteConversation(
            @PathVariable @NotBlank String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );
}
