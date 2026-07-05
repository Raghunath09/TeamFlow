package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.CreateNotificationRequest;
import com.teamflow.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse createNotification(CreateNotificationRequest request);

    List<NotificationResponse> getNotificationsByUser(Long userId);

    NotificationResponse markAsRead(Long notificationId);

    void deleteNotification(Long notificationId);

}