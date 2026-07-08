package com.teamflow.service.impl;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.teamflow.dto.CommentResponse;
import com.teamflow.dto.CreateCommentRequest;
import com.teamflow.entity.Comment;
import com.teamflow.entity.Task;
import com.teamflow.entity.User;
import com.teamflow.repository.CommentRepository;
import com.teamflow.repository.TaskRepository;
import com.teamflow.repository.UserRepository;
import com.teamflow.security.SecurityUtils;
import com.teamflow.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public CommentResponse addComment(CreateCommentRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .task(task)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return CommentResponse.builder()
                .id(savedComment.getId())
                .content(savedComment.getContent())
                .userId(user.getId())
                .userName(user.getFullName())
                .taskId(task.getId())
                .taskTitle(task.getTitle())
                .createdAt(savedComment.getCreatedAt())
                .build();
    }

    @Override
    public List<CommentResponse> getCommentsByTask(Long taskId) {

        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .userId(comment.getUser().getId())
                        .userName(comment.getUser().getFullName())
                        .taskId(comment.getTask().getId())
                        .taskTitle(comment.getTask().getTitle())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
        public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        String email = SecurityUtils.getCurrentUserEmail();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = SecurityUtils.isAdmin();

        boolean isOwner =
                comment.getUser().getId().equals(currentUser.getId());

        if (!isAdmin && !isOwner) {
                throw new AccessDeniedException(
                        "You can delete only your own comments.");
        }

        commentRepository.delete(comment);
        }
}