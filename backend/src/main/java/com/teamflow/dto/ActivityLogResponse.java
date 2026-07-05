package com.teamflow.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityLogResponse {

    private Long id;

    private String action;

    private Long userId;

    private String userName;

    private Long projectId;

    private String projectName;

    private LocalDateTime createdAt;

}