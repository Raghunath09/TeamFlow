package com.teamflow.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamflow.dto.CreateProjectMemberRequest;
import com.teamflow.dto.ProjectMemberResponse;
import com.teamflow.entity.Project;
import com.teamflow.entity.ProjectMember;
import com.teamflow.entity.User;
import com.teamflow.repository.ProjectMemberRepository;
import com.teamflow.repository.ProjectRepository;
import com.teamflow.repository.UserRepository;
import com.teamflow.service.ProjectMemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
public ProjectMemberResponse addMember(CreateProjectMemberRequest request) {

    Project project = projectRepository.findById(request.getProjectId())
            .orElseThrow(() -> new RuntimeException("Project not found"));

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    ProjectMember projectMember = ProjectMember.builder()
            .project(project)
            .user(user)
            .build();

    ProjectMember savedMember = projectMemberRepository.save(projectMember);

    return ProjectMemberResponse.builder()
        .id(savedMember.getId())
        .projectId(project.getId())
        .projectName(project.getProjectName())
        .userId(user.getId())
        .userName(user.getFullName())
        .joinedAt(savedMember.getJoinedAt())
        .build();
}

@Override
public List<ProjectMemberResponse> getMembersByProject(Long projectId) {

    return projectMemberRepository.findByProjectId(projectId)
            .stream()
            .map(member -> ProjectMemberResponse.builder()
                    .id(member.getId())
                    .projectId(member.getProject().getId())
                    .projectName(member.getProject().getProjectName())
                    .userId(member.getUser().getId())
                    .userName(member.getUser().getFullName())
                    .joinedAt(member.getJoinedAt())
                    .build())
            .toList();
}

@Override
public void removeMember(Long memberId) {

    ProjectMember member = projectMemberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Member not found"));

    projectMemberRepository.delete(member);
}

}