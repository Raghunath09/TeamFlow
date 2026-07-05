package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.CreateProjectRequest;
import com.teamflow.dto.ProjectResponse;

public interface ProjectService {

    ProjectResponse createProject(CreateProjectRequest request);

    List<ProjectResponse> getAllProjects();

    ProjectResponse getProjectById(Long id);

    ProjectResponse updateProject(Long id, CreateProjectRequest request);

    void deleteProject(Long id);
}