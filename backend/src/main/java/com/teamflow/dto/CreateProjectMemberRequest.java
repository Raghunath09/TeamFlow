package com.teamflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectMemberRequest {

    @NotNull
    private Long projectId;

    @NotNull
    private Long userId;
}