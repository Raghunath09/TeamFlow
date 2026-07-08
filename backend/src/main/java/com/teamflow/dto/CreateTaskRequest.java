package com.teamflow.dto;

import java.time.LocalDate;

import com.teamflow.enums.Priority;
import com.teamflow.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank
    private String title;

    private String description;

    private Priority priority;

    private LocalDate dueDate;
    
    private TaskStatus status;

    @NotNull
    private Long projectId;

    @NotNull
    private Long assigneeId;
}