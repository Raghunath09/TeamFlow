package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.CreateTaskRequest;
import com.teamflow.dto.TaskResponse;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse updateTask(Long id, CreateTaskRequest request);

    void deleteTask(Long id);
}