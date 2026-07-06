import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getTasks = () => {
    return api.get("/tasks", getHeaders());
};

export const createTask = (task) => {
    return api.post("/tasks", task, getHeaders());
};

export const updateTask = (id, task) => {
    return api.put(`/tasks/${id}`, task, getHeaders());
};

export const deleteTask = (id) => {
    return api.delete(`/tasks/${id}`, getHeaders());
};