import api from "../api/axios";

export const getDashboard = () => {
    const token = localStorage.getItem("token");

    return api.get("/dashboard", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};