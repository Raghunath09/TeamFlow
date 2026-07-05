package com.teamflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.teamflow.enums.Priority;
import com.teamflow.enums.TaskStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private Priority priority;

    private LocalDate dueDate;

    private Long projectId;

    private Long assigneeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}