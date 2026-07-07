import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getProfile = (id) => {
    return api.get(`/users/${id}`, getHeaders());
};

export const updateProfile = (id, data) => {
    return api.put(`/users/${id}`, data, getHeaders());
};

export const getUsers = () => {
    return api.get("/users", getHeaders());
};