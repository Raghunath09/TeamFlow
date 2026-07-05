package com.teamflow.service.impl;

import java.util.List;

import com.teamflow.entity.Notification;
import com.teamflow.entity.User;

import org.springframework.stereotype.Service;

import com.teamflow.dto.CreateNotificationRequest;
import com.teamflow.dto.NotificationResponse;
import com.teamflow.repository.NotificationRepository;
import com.teamflow.repository.UserRepository;
import com.teamflow.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

   @Override
    public NotificationResponse createNotification(CreateNotificationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .message(request.getMessage())
                .user(user)
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        return NotificationResponse.builder()
                .id(savedNotification.getId())
                .title(savedNotification.getTitle())
                .message(savedNotification.getMessage())
                .isRead(savedNotification.isRead())
                .userId(user.getId())
                .userName(user.getFullName())
                .createdAt(savedNotification.getCreatedAt())
                .build();
    }

    @Override
    public List<NotificationResponse> getNotificationsByUser(Long userId) {

        return notificationRepository.findByUserId(userId)
                .stream()
                .map(notification -> NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .message(notification.getMessage())
                        .isRead(notification.isRead())
                        .userId(notification.getUser().getId())
                        .userName(notification.getUser().getFullName())
                        .createdAt(notification.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public NotificationResponse markAsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .userId(notification.getUser().getId())
                .userName(notification.getUser().getFullName())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    @Override
    public void deleteNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notificationRepository.delete(notification);
    }
}