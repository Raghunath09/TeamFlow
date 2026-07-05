package com.teamflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.DashboardResponse;
import com.teamflow.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard fetched successfully",
                        dashboardService.getDashboard()
                )
        );
    }
}