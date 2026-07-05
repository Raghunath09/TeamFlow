package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.CreateProjectMemberRequest;
import com.teamflow.dto.ProjectMemberResponse;

public interface ProjectMemberService {

    ProjectMemberResponse addMember(CreateProjectMemberRequest request);

    List<ProjectMemberResponse> getMembersByProject(Long projectId);

    void removeMember(Long memberId);

}