import api from "../api/axios";

const getHeaders = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
});

export const getProjects = () => {
    return api.get("/projects", getHeaders());
};

export const createProject = (project) => {
    return api.post("/projects", project, getHeaders());
};

export const updateProject = (id, project) => {
    return api.put(`/projects/${id}`, project, getHeaders());
};

export const deleteProject = (id) => {
    return api.delete(`/projects/${id}`, getHeaders());
};