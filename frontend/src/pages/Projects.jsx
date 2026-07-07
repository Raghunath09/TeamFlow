import { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import {
    getProjects,
    createProject,
    updateProject,
    deleteProject,
} from "../services/projectService";
import Loading from "../components/Loading";
import toast from "react-hot-toast";

function Projects() {

    const [projects, setProjects] = useState([]);
    const [loading, setLoading] = useState(true);

    const user = JSON.parse(localStorage.getItem("user"));

    const isAdmin = user?.role === "ADMIN";

    const [search, setSearch] = useState("");

    const [showModal, setShowModal] = useState(false);

    const [editingId, setEditingId] = useState(null);

    const [project, setProject] = useState({
        projectName: "",
        description: "",
        startDate: "",
        endDate: "",
        status: "ACTIVE",
    });

    useEffect(() => {

        fetchProjects();

    }, []);

    const fetchProjects = async () => {

        try {

            const response = await getProjects();

            setProjects(response.data.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const filteredProjects = projects.filter((project) =>
        project.projectName
            .toLowerCase()
            .includes(search.toLowerCase())
    );

    const saveProject = async () => {

        try {

            if (editingId) {

                await updateProject(editingId, project);

            } else {

                await createProject(project);

            }

            toast.success(
                editingId
                    ? "Project Updated Successfully"
                    : "Project Created Successfully"
            );

            setShowModal(false);

            setEditingId(null);

            setProject({
                projectName: "",
                description: "",
                startDate: "",
                endDate: "",
                status: "ACTIVE",
            });

            fetchProjects();

        } catch (error) {

            console.error(error);

            toast.error("Failed to save project");

        }

    };

    const removeProject = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this project?"
        );

        if (!confirmed) return;

        try {

            await deleteProject(id);

            toast.success("Project Deleted Successfully");

            fetchProjects();

        } catch (error) {

            console.error(error);

            toast.error("Failed to delete project");

        }

    };

    const editProject = (project) => {

        setProject({
            projectName: project.projectName,
            description: project.description,
            startDate: project.startDate,
            endDate: project.endDate,
            status: project.status,
        });

        setEditingId(project.id);

        setShowModal(true);

    };

    if (loading) {

        return (

            <MainLayout>

                <Loading />

            </MainLayout>

        );

    }

    return (

        <MainLayout>

                        <div className="flex flex-col md:flex-row md:justify-between md:items-center gap-4 mb-8">

                <h1 className="text-3xl md:text-4xl font-bold text-black dark:text-white">
                    Projects
                </h1>

                {isAdmin && (
                <button
                    onClick={() => {

                        setEditingId(null);

                        setProject({
                            projectName: "",
                            description: "",
                            startDate: "",
                            endDate: "",
                            status: "ACTIVE",
                        });

                        setShowModal(true);

                    }}
                    className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-3 rounded-lg transition duration-200"
                >
                    + New Project
                </button>
                )}

            </div>

            <div className="mb-6">

                <input
                    type="text"
                    placeholder="🔍 Search Projects..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className="w-full md:w-96 border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded-lg outline-none focus:ring-2 focus:ring-blue-500 transition"
                />

            </div>

            <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg overflow-x-auto transition-colors duration-300">

                <table className="min-w-full">

                    <thead className="bg-gray-100 dark:bg-slate-700">

                        <tr>

                            <th className="text-left p-4 text-black dark:text-white">
                                Project
                            </th>

                            <th className="text-left p-4 text-black dark:text-white">
                                Status
                            </th>

                            <th className="text-left p-4 text-black dark:text-white">
                                Start Date
                            </th>

                            <th className="text-left p-4 text-black dark:text-white">
                                Actions
                            </th>

                        </tr>

                    </thead>

                    <tbody>

                        {filteredProjects.length === 0 ? (

                            <tr>

                                <td
                                    colSpan="4"
                                    className="text-center py-10 text-gray-500 dark:text-gray-300"
                                >
                                    No Projects Found
                                </td>

                            </tr>

                        ) : (

                            filteredProjects.map((project) => (
                                                                <tr
                                    key={project.id}
                                    className="border-t border-gray-200 dark:border-slate-700 hover:bg-gray-50 dark:hover:bg-slate-700 transition"
                                >

                                    <td className="p-4 text-black dark:text-white font-medium">
                                        {project.projectName}
                                    </td>

                                    <td className="p-4">

                                        <span className="bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm font-medium">

                                            {project.status}

                                        </span>

                                    </td>

                                    <td className="p-4 text-black dark:text-white">

                                        {project.startDate}

                                    </td>

                                    <td className="p-4">

                                            <div className="flex flex-wrap gap-2">

                                                {isAdmin && (
                                                    <>
                                                        <button
                                                            onClick={() => editProject(project)}
                                                            className="bg-yellow-500 hover:bg-yellow-600 text-white px-4 py-2 rounded-lg transition"
                                                        >
                                                            Edit
                                                        </button>

                                                        <button
                                                            onClick={() => removeProject(project.id)}
                                                            className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg transition"
                                                        >
                                                            Delete
                                                        </button>
                                                    </>
                                                )}

                                            </div>

                                        </td>

                                </tr>

                            ))

                        )}

                    </tbody>

                </table>

            </div>

            {showModal && (

                <div className="fixed inset-0 bg-black/50 flex justify-center items-center p-4">
                                        <div className="bg-white dark:bg-slate-800 rounded-xl p-6 md:p-8 w-[95%] md:w-[500px] transition-colors duration-300 shadow-xl">

                        <h2 className="text-2xl font-bold mb-6 text-black dark:text-white">

                            {editingId ? "Edit Project" : "Create Project"}

                        </h2>

                        <input
                            type="text"
                            placeholder="Project Name"
                            value={project.projectName}
                            onChange={(e) =>
                                setProject({
                                    ...project,
                                    projectName: e.target.value,
                                })
                            }
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded-lg p-3 mb-4 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                        <textarea
                            rows="4"
                            placeholder="Description"
                            value={project.description}
                            onChange={(e) =>
                                setProject({
                                    ...project,
                                    description: e.target.value,
                                })
                            }
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded-lg p-3 mb-4 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                        <input
                            type="date"
                            value={project.startDate}
                            onChange={(e) =>
                                setProject({
                                    ...project,
                                    startDate: e.target.value,
                                })
                            }
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded-lg p-3 mb-4 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                        <input
                            type="date"
                            value={project.endDate}
                            onChange={(e) =>
                                setProject({
                                    ...project,
                                    endDate: e.target.value,
                                })
                            }
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded-lg p-3 mb-4 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                        <select
                            value={project.status}
                            onChange={(e) =>
                                setProject({
                                    ...project,
                                    status: e.target.value,
                                })
                            }
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded-lg p-3 mb-6 outline-none focus:ring-2 focus:ring-blue-500"
                        >
                            <option value="ACTIVE">ACTIVE</option>
                            <option value="COMPLETED">COMPLETED</option>
                            <option value="ON_HOLD">ON HOLD</option>
                        </select>

                        <div className="flex flex-col-reverse md:flex-row justify-end gap-3">

                            <button
                                onClick={() => setShowModal(false)}
                                className="px-5 py-2 bg-gray-500 hover:bg-gray-600 text-white rounded-lg transition"
                            >
                                Cancel
                            </button>

                            <button
                                onClick={saveProject}
                                className="px-5 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg transition"
                            >
                                {editingId ? "Update" : "Save"}
                            </button>

                        </div>

                    </div>

                </div>

            )}

        </MainLayout>

    );

}

export default Projects;

                