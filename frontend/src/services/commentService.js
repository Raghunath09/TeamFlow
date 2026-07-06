import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getComments = (taskId) => {
    return api.get(`/comments/task/${taskId}`, getHeaders());
};

export const addComment = (comment) => {
    return api.post("/comments", comment, getHeaders());
};

export const deleteComment = (id) => {
    return api.delete(`/comments/${id}`, getHeaders());
};