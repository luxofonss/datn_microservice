package com.quyennv.lms.presenter.rest.api.conversation;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.CreateConversationRequest;
import com.quyennv.lms.presenter.rest.dto.UpdateConversationRequest;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/conversations")
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
