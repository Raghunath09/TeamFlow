package com.teamflow.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private String title;

    private String message;

    @JsonProperty("isRead")
    private boolean isRead;

    private Long userId;

    private String userName;

    private LocalDateTime createdAt;
}