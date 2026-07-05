package com.teamflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAttachmentRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String filePath;

    @NotNull
    private Long taskId;

}