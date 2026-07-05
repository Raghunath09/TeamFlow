package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.ActivityLogResponse;
import com.teamflow.dto.CreateActivityLogRequest;
import com.teamflow.service.ActivityLogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @PostMapping
    public ResponseEntity<ApiResponse<ActivityLogResponse>> createActivity(
            @Valid @RequestBody CreateActivityLogRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Activity created successfully",
                        activityLogService.createActivity(request)
                )
        );
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ActivityLogResponse>>> getProjectActivities(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Activities fetched successfully",
                        activityLogService.getProjectActivities(projectId)
                )
        );
    }
}