package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.CreateProjectMemberRequest;
import com.teamflow.dto.ProjectMemberResponse;
import com.teamflow.service.ProjectMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/project-members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectMemberResponse>> addMember(
            @Valid @RequestBody CreateProjectMemberRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Member added successfully",
                        projectMemberService.addMember(request)
                )
        );
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ProjectMemberResponse>>> getMembersByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Members fetched successfully",
                        projectMemberService.getMembersByProject(projectId)
                )
        );
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<String>> removeMember(
            @PathVariable Long memberId) {

        projectMemberService.removeMember(memberId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Member removed successfully",
                        "Deleted"
                )
        );
    }
}