package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.AttachmentResponse;
import com.teamflow.dto.CreateAttachmentRequest;

public interface AttachmentService {

    AttachmentResponse addAttachment(CreateAttachmentRequest request);

    List<AttachmentResponse> getAttachmentsByTask(Long taskId);

    void deleteAttachment(Long attachmentId);

}