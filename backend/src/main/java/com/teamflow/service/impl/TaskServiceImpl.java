package com.teamflow.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.teamflow.dto.CreateTaskRequest;
import com.teamflow.dto.TaskResponse;
import com.teamflow.entity.Project;
import com.teamflow.entity.Task;
import com.teamflow.entity.User;
import com.teamflow.exception.ResourceNotFoundException;
import com.teamflow.repository.ProjectRepository;
import com.teamflow.repository.TaskRepository;
import com.teamflow.repository.UserRepository;
import com.teamflow.security.SecurityUtils;
import com.teamflow.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
        public TaskResponse createTask(CreateTaskRequest request) {

        if (!SecurityUtils.isAdmin()) {
                throw new AccessDeniedException("Only admins can create tasks.");
        }

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .project(project)
                .assignee(assignee)
                .build();

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
        }

    @Override
    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return mapToResponse(task);
    }

    @Override
        public TaskResponse updateTask(Long id, CreateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        String email = SecurityUtils.getCurrentUserEmail();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = SecurityUtils.isAdmin();

        boolean isAssignee =
                task.getAssignee().getId().equals(currentUser.getId());

        if (!isAdmin && !isAssignee) {
                throw new AccessDeniedException(
                        "You can update only your assigned tasks.");
        }

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        if (isAdmin) {
        task.setProject(project);
        task.setAssignee(assignee);
}
        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
        }

    @Override
        public void deleteTask(Long id) {

        if (!SecurityUtils.isAdmin()) {
                throw new AccessDeniedException("Only admins can delete tasks.");
        }

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
        }

    private TaskResponse mapToResponse(Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .projectId(task.getProject().getId())
                .assigneeId(task.getAssignee().getId())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}