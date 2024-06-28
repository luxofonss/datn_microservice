package com.quyennv.datn.notification_service.repository;

import com.quyennv.datn.notification_service.entity.Notification;

import java.util.List;

public interface NotificationRepository {
    int insert(Notification notification);

    List<Notification> getNotificationsByUserId(String userId);
}
