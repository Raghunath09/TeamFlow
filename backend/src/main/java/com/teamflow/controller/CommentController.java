package com.teamflow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.CommentResponse;
import com.teamflow.dto.CreateCommentRequest;
import com.teamflow.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommentResponse>> addComment(
            @Valid @RequestBody CreateCommentRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Comment added successfully",
                        commentService.addComment(request)
                )
        );
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsByTask(
            @PathVariable Long taskId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Comments fetched successfully",
                        commentService.getCommentsByTask(taskId)
                )
        );
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<String>> deleteComment(
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Comment deleted successfully",
                        "Deleted"
                )
        );
    }
}