package com.teamflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.teamflow.enums.ProjectStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

    private Long id;

    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private ProjectStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}