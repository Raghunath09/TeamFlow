package com.teamflow.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProjectRequest {

    @NotBlank
    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}