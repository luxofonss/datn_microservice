package com.quyennv.datn.notification_service.mapper;

import com.quyennv.datn.notification_service.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface NotificationMapper {
    List<Notification> findAll();

    Notification findById(UUID id);

    //
    int deleteById(UUID id);

    int deleteAll();

    //
    int insert(Notification employee);

    //
    int update(Notification employee);

    List<Notification> getNotificationsByUserId(UUID userId);
}