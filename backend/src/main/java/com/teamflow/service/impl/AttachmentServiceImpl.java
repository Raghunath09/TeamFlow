package com.teamflow.service.impl;

import java.util.List;

import com.teamflow.entity.Attachment;
import com.teamflow.entity.Task;

import org.springframework.stereotype.Service;

import com.teamflow.dto.AttachmentResponse;
import com.teamflow.dto.CreateAttachmentRequest;
import com.teamflow.entity.Attachment;
import com.teamflow.repository.AttachmentRepository;
import com.teamflow.repository.TaskRepository;
import com.teamflow.service.AttachmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;

    @Override
    public AttachmentResponse addAttachment(CreateAttachmentRequest request) {

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Attachment attachment = Attachment.builder()
                .fileName(request.getFileName())
                .filePath(request.getFilePath())
                .task(task)
                .build();

        Attachment savedAttachment = attachmentRepository.save(attachment);

        return AttachmentResponse.builder()
                .id(savedAttachment.getId())
                .fileName(savedAttachment.getFileName())
                .filePath(savedAttachment.getFilePath())
                .taskId(task.getId())
                .taskTitle(task.getTitle())
                .build();
    }

    @Override
    public List<AttachmentResponse> getAttachmentsByTask(Long taskId) {

        return attachmentRepository.findByTaskId(taskId)
                .stream()
                .map(attachment -> AttachmentResponse.builder()
                        .id(attachment.getId())
                        .fileName(attachment.getFileName())
                        .filePath(attachment.getFilePath())
                        .taskId(attachment.getTask().getId())
                        .taskTitle(attachment.getTask().getTitle())
                        .build())
                .toList();
    }

    @Override
    public void deleteAttachment(Long attachmentId) {

        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        attachmentRepository.delete(attachment);
    }
}