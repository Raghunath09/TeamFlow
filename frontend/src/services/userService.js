import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getProfile = (userId) => {
    return api.get(`/users/${userId}`, getHeaders());
};

export const updateProfile = (userId, profile) => {
    return api.put(`/users/${userId}`, profile, getHeaders());
};