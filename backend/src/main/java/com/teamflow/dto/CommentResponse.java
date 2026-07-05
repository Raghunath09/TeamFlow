package com.teamflow.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {

    private Long id;

    private String content;

    private Long userId;

    private String userName;

    private Long taskId;

    private String taskTitle;

    private LocalDateTime createdAt;
}