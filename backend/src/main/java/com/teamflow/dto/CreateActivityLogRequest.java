package com.teamflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateActivityLogRequest {

    @NotBlank
    private String action;

    @NotNull
    private Long userId;

    @NotNull
    private Long projectId;

}