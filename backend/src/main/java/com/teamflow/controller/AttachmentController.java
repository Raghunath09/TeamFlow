package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.AttachmentResponse;
import com.teamflow.dto.CreateAttachmentRequest;
import com.teamflow.service.AttachmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<AttachmentResponse>> addAttachment(
            @Valid @RequestBody CreateAttachmentRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Attachment added successfully",
                        attachmentService.addAttachment(request)
                )
        );
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<List<AttachmentResponse>>> getAttachmentsByTask(
            @PathVariable Long taskId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Attachments fetched successfully",
                        attachmentService.getAttachmentsByTask(taskId)
                )
        );
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<ApiResponse<String>> deleteAttachment(
            @PathVariable Long attachmentId) {

        attachmentService.deleteAttachment(attachmentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Attachment deleted successfully",
                        "Deleted"
                )
        );
    }
}