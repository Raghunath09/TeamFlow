package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.CreateNotificationRequest;
import com.teamflow.dto.NotificationResponse;
import com.teamflow.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<NotificationResponse>> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notification created successfully",
                        notificationService.createNotification(request)
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getNotificationsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notifications fetched successfully",
                        notificationService.getNotificationsByUser(userId)
                )
        );
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<NotificationResponse>> markAsRead(
            @PathVariable Long notificationId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notification marked as read",
                        notificationService.markAsRead(notificationId)
                )
        );
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<String>> deleteNotification(
            @PathVariable Long notificationId) {

        notificationService.deleteNotification(notificationId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Notification deleted successfully",
                        "Deleted"
                )
        );
    }
}