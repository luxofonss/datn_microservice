package com.quyennv.datn.notification_service.controller;

import com.quyennv.datn.notification_service.dto.ApiResponse;
import com.quyennv.datn.notification_service.entity.Notification;
import com.quyennv.datn.notification_service.services.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Notification>>> getNotification(
            @RequestParam(required = true) String userId,
            HttpServletRequest request) {
        List<Notification> notifications = notificationService.getNotificationsByUser(userId);

        ApiResponse<List<Notification>> apiResponse = new ApiResponse<>(true, "Get notifications successfully", notifications);

        return ResponseEntity.ok(apiResponse);
    }
}
