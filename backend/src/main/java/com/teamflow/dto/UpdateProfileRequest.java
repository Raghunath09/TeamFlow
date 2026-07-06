package com.teamflow.dto;

import com.teamflow.enums.Theme;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @NotBlank
    private String fullName;

    private Theme theme;

}