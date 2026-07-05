package com.teamflow.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectMemberResponse {

    private Long id;

    private Long projectId;

    private String projectName;

    private Long userId;

    private String userName;

    private LocalDateTime joinedAt;
}