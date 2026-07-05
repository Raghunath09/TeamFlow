package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.CommentResponse;
import com.teamflow.dto.CreateCommentRequest;

public interface CommentService {

    CommentResponse addComment(CreateCommentRequest request);

    List<CommentResponse> getCommentsByTask(Long taskId);

    void deleteComment(Long commentId);

}