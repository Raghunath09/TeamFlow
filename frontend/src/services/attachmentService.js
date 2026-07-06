import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getAttachments = (taskId) => {
    return api.get(`/attachments/task/${taskId}`, getHeaders());
};

export const createAttachment = (attachment) => {
    return api.post("/attachments", attachment, getHeaders());
};

export const deleteAttachment = (id) => {
    return api.delete(`/attachments/${id}`, getHeaders());
};