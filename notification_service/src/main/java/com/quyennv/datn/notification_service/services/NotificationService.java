package com.quyennv.datn.notification_service.services;

import com.quyennv.datn.notification_service.entity.Notification;
import com.quyennv.datn.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public int insert(Notification notification) {
        return notificationRepository.insert(notification);
    }

    public List<Notification> getNotificationsByUser(String userId) {
        return notificationRepository.getNotificationsByUserId(userId);
    }
}
