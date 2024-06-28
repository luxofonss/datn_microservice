package com.quyennv.datn.notification_service.repository.impl;

import com.quyennv.datn.notification_service.entity.Notification;
import com.quyennv.datn.notification_service.mapper.NotificationMapper;
import com.quyennv.datn.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationMapper notificationMapper;

    public NotificationRepositoryImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public int insert(Notification notification) {
        return notificationMapper.insert(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationMapper.getNotificationsByUserId(UUID.fromString(userId));
    }
}
