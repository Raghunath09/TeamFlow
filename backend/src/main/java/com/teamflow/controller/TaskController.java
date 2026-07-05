package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.CreateTaskRequest;
import com.teamflow.dto.TaskResponse;
import com.teamflow.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Task created successfully",
                        taskService.createTask(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Tasks fetched successfully",
                        taskService.getAllTasks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Task fetched successfully",
                        taskService.getTaskById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Task updated successfully",
                        taskService.updateTask(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Task deleted successfully",
                        "Deleted"));
    }
}