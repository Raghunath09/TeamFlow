package com.teamflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNotificationRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotNull
    private Long userId;
}