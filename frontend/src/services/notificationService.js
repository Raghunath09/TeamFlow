import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getNotifications = (userId) => {
    return api.get(`/notifications/user/${userId}`, getHeaders());
};

export const createNotification = (notification) => {
    return api.post("/notifications", notification, getHeaders());
};

export const markAsRead = (id) => {
    return api.put(`/notifications/${id}/read`, {}, getHeaders());
};

export const deleteNotification = (id) => {
    return api.delete(`/notifications/${id}`, getHeaders());
};