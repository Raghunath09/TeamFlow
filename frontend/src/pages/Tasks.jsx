import { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";

import {
    getTasks,
    createTask,
    updateTask,
    deleteTask,
} from "../services/taskService";

import {
    getComments,
    addComment,
    deleteComment as deleteCommentService,
} from "../services/commentService";

import {
    getAttachments,
    createAttachment,
    deleteAttachment as deleteAttachmentService,
} from "../services/attachmentService";
import Loading from "../components/Loading";
import toast from "react-hot-toast";

function Tasks() {

    const [editingId, setEditingId] = useState(null);

    const [tasks, setTasks] = useState([]);

    const [loading, setLoading] = useState(true);

    const [search, setSearch] = useState("");

    const [showModal, setShowModal] = useState(false);

    const [showComments, setShowComments] = useState(false);

    const [showAttachments, setShowAttachments] = useState(false);

    const [comments, setComments] = useState([]);

    const [attachments, setAttachments] = useState([]);

    const [selectedTask, setSelectedTask] = useState(null);

    const [commentText, setCommentText] = useState("");

    const [attachment, setAttachment] = useState({
        fileName: "",
        filePath: "",
    });

    const [task, setTask] = useState({
        title: "",
        description: "",
        priority: "MEDIUM",
        dueDate: "",
        projectId: 2,
        assigneeId: 2,
    });

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {

        try {

            const response = await getTasks();

            setTasks(response.data.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const editTask = (task) => {

                setTask({
                    title: task.title,
                    description: task.description,
                    priority: task.priority,
                    dueDate: task.dueDate,
                    projectId: task.projectId,
                    assigneeId: task.assigneeId,
                });

                setEditingId(task.id);

                setShowModal(true);

        };

        const removeTask = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this task?"
        );

        if (!confirmed) return;

        try {

            await deleteTask(id);

            toast.success("Task Deleted Successfully");

            fetchTasks();

        } catch (error) {

            console.error(error);

            toast.error("Failed to delete task");

        }

    };

    const filteredTasks = tasks.filter((task) =>
        task.title.toLowerCase().includes(search.toLowerCase())
    );

    const openComments = async (task) => {

        setSelectedTask(task);

        setShowComments(true);

        try {

            const response = await getComments(task.id);

            setComments(response.data.data);

        } catch (error) {

            console.error(error);

        }

    };

    const openAttachments = async (task) => {

        setSelectedTask(task);

        setShowAttachments(true);

        try {

            const response = await getAttachments(task.id);

            setAttachments(response.data.data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleAttachmentSave = async () => {

        if (!attachment.fileName.trim() || !attachment.filePath.trim()) {
            return;
        }

        try {

            await createAttachment({
                fileName: attachment.fileName,
                filePath: attachment.filePath,
                taskId: selectedTask.id,
            });

            const response = await getAttachments(selectedTask.id);

            setAttachments(response.data.data);

            setAttachment({
                fileName: "",
                filePath: "",
            });

            toast.success("Attachment Added Successfully");

        } catch (error) {

            console.error(error);

            toast.error("Failed to add attachment");

        }

    };

    const handleDeleteAttachment = async (attachmentId) => {

        try {

            await deleteAttachmentService(attachmentId);

            const response = await getAttachments(selectedTask.id);

            setAttachments(response.data.data);

            toast.success("Attachment Deleted Successfully");

        } catch (error) {

            console.error(error);

            toast.error("Failed to delete attachment");

        }

    };

    const handleCommentSave = async () => {

        if (!commentText.trim()) {
            return;
        }

        try {

            await addComment({
                content: commentText,
                taskId: selectedTask.id,
                userId: 2,
            });

            const response = await getComments(selectedTask.id);

            setComments(response.data.data);

            setCommentText("");
            toast.success("Comment Added Successfully");

        } catch (error) {

            console.error(error);

            toast.error("Failed to add comment");

        }

    };

    const handleDeleteComment = async (commentId) => {

        try {

            await deleteCommentService(commentId);

            const response = await getComments(selectedTask.id);

            setComments(response.data.data);
            toast.success("Comment Deleted Successfully");

        } catch (error) {

            console.error(error);

            toast.error("Failed to delete comment");

        }

    };

    const handleSave = async () => {

        try {

            if (editingId) {

                await updateTask(editingId, task);

                toast.success("Task Updated Successfully");

            } else {

                await createTask(task);

                toast.success("Task Created Successfully");

            }

            fetchTasks();

            setShowModal(false);
            setEditingId(null);

            setTask({
                title: "",
                description: "",
                priority: "MEDIUM",
                dueDate: "",
                projectId: 2,
                assigneeId: 2,
            });
        } catch (error) {

            console.error(error);

            toast.error(
                editingId
                    ? "Failed to update task"
                    : "Failed to create task"
            );

        }

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
                    Tasks
                </h1>

                <button
                    onClick={() => {

                        setEditingId(null);

                        setTask({
                            title: "",
                            description: "",
                            priority: "MEDIUM",
                            dueDate: "",
                            projectId: 2,
                            assigneeId: 2,
                        });

                        setShowModal(true);

                    }}
                    className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-3 rounded-lg transition duration-200"
                >
                    + New Task
                </button>

            </div>

            <div className="mb-5">

                <input
                    type="text"
                    placeholder="🔍 Search Tasks..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className="w-full md:w-96 border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded-lg"
                />

            </div>

            <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg overflow-x-auto transition-colors duration-300">

                <table className="min-w-full">

                    <thead className="bg-gray-100 dark:bg-slate-700">

                        <tr>

                            <th className="text-left p-4 text-gray-700 dark:text-white">
                                Task
                            </th>

                            <th className="text-left p-4 text-gray-700 dark:text-white">
                                Priority
                            </th>

                            <th className="text-left p-4 text-gray-700 dark:text-white">
                                Status
                            </th>

                            <th className="text-left p-4 text-gray-700 dark:text-white">
                                Due Date
                            </th>

                            <th className="text-left p-4 text-gray-700 dark:text-white">
                                Actions
                            </th>

                        </tr>

                    </thead>

                    <tbody>

                        {tasks.length === 0 ? (

                            <tr>

                                <td
                                    colSpan="5"
                                    className="text-center p-8 text-gray-500 dark:text-gray-300"
                                >
                                    No tasks found
                                </td>

                            </tr>

                        ) : (

                            filteredTasks.map(task => (

                                <tr
                                    key={task.id}
                                    className="border-t border-gray-200 dark:border-slate-700 hover:bg-gray-50 dark:hover:bg-slate-700 transition"
                                >

                                    <td className="p-4 text-black dark:text-white">
                                        {task.title}
                                    </td>

                                    <td className="p-4">

                                        <span className="bg-yellow-100 text-yellow-700 px-3 py-1 rounded-full text-sm">

                                            {task.priority}

                                        </span>

                                    </td>

                                    <td className="p-4">

                                        <span className="bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm">

                                            {task.status}

                                        </span>

                                    </td>

                                    <td className="p-4 text-black dark:text-white">

                                        {task.dueDate}

                                    </td>

                                    <td className="p-4">
                                    <div className="flex flex-wrap gap-2">

                                        <button
                                            onClick={() => openComments(task)}
                                            className="bg-indigo-600 text-white px-3 py-1 rounded"
                                        >
                                            Comments
                                        </button>

                                        <button
                                            onClick={() => openAttachments(task)}
                                            className="bg-gray-700 text-white px-3 py-1 rounded"
                                        >
                                            Attachments
                                        </button>

                                        <button
                                            onClick={() => editTask(task)}
                                            className="bg-yellow-500 text-white px-3 py-1 rounded"
                                        >
                                            Edit
                                        </button>

                                        <button
                                            onClick={() => removeTask(task.id)}
                                            className="bg-red-600 text-white px-3 py-1 rounded"
                                        >
                                            Delete
                                        </button>
                                    </div>

                                    </td>

                                </tr>

                            ))

                        )}

                    </tbody>

                </table>

            </div>

                        {showModal && (

                <div className="fixed inset-0 bg-black/50 flex items-center justify-center">

                    <div className="bg-white dark:bg-slate-800 rounded-xl p-6 md:p-8 w-[95%] md:w-[500px] transition-colors duration-300 shadow-xl">

                        <h2 className="text-2xl font-bold mb-5 text-black dark:text-white">

                            {editingId ? "Edit Task" : "Create Task"}

                        </h2>

                        <input
                            type="text"
                            placeholder="Task Title"
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded mb-4"
                            value={task.title}
                            onChange={(e) =>
                                setTask({
                                    ...task,
                                    title: e.target.value,
                                })
                            }
                        />

                        <textarea
                            placeholder="Description"
                            rows="4"
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded mb-4"
                            value={task.description}
                            onChange={(e) =>
                                setTask({
                                    ...task,
                                    description: e.target.value,
                                })
                            }
                        />

                        <select
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded mb-4"
                            value={task.priority}
                            onChange={(e) =>
                                setTask({
                                    ...task,
                                    priority: e.target.value,
                                })
                            }
                        >
                            <option value="LOW">LOW</option>
                            <option value="MEDIUM">MEDIUM</option>
                            <option value="HIGH">HIGH</option>
                        </select>

                        <input
                            type="date"
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white p-3 rounded mb-4"
                            value={task.dueDate}
                            onChange={(e) =>
                                setTask({
                                    ...task,
                                    dueDate: e.target.value,
                                })
                            }
                        />

                        <div className="flex justify-end gap-3">

                            <button
                                onClick={() => setShowModal(false)}
                                className="px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white rounded"
                            >
                                Cancel
                            </button>

                            <button
                                onClick={handleSave}
                                className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded"
                            >
                                {editingId ? "Update" : "Save"}
                            </button>

                        </div>

                    </div>

                </div>

            )}

                        {showComments && (

                <div className="fixed inset-0 bg-black/50 flex items-center justify-center">

                    <div className="bg-white dark:bg-slate-800 rounded-xl p-6 md:p-8 w-[95%] md:w-[600px] max-h-[80vh] overflow-y-auto transition-colors duration-300 shadow-xl">

                        <div className="flex justify-between items-center mb-5">

                            <h2 className="text-2xl font-bold text-black dark:text-white">
                                Comments
                            </h2>

                            <button
                                onClick={() => setShowComments(false)}
                                className="text-gray-500 dark:text-gray-300 text-2xl"
                            >
                                ×
                            </button>

                        </div>

                        <div className="space-y-4 mb-6">

                            {comments.length === 0 ? (

                                <p className="text-gray-500 dark:text-gray-300">
                                    No comments yet.
                                </p>

                            ) : (

                                comments.map(comment => (

                                    <div
                                        key={comment.id}
                                        className="border border-gray-300 dark:border-slate-600 rounded-lg p-4 bg-white dark:bg-slate-700"
                                    >

                                        <div className="font-semibold text-black dark:text-white">
                                            {comment.userName}
                                        </div>

                                        <div className="text-gray-700 dark:text-gray-200 mt-1">
                                            {comment.content}
                                        </div>

                                        <div className="flex justify-between items-center mt-3">

                                            <span className="text-xs text-gray-400">
                                                {comment.createdAt}
                                            </span>

                                            <button
                                                onClick={() => handleDeleteComment(comment.id)}
                                                className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded text-sm"
                                            >
                                                Delete
                                            </button>

                                        </div>

                                    </div>

                                ))

                            )}

                        </div>

                        <textarea
                            rows="3"
                            placeholder="Write a comment..."
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded p-3"
                            value={commentText}
                            onChange={(e) => setCommentText(e.target.value)}
                        />

                        <div className="flex justify-end mt-4 gap-3">

                            <button
                                onClick={() => setShowComments(false)}
                                className="px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white rounded"
                            >
                                Close
                            </button>

                            <button
                                onClick={handleCommentSave}
                                className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded"
                            >
                                Send
                            </button>

                        </div>

                    </div>

                </div>

            )}

                        {showAttachments && (

                <div className="fixed inset-0 bg-black/50 flex items-center justify-center">

                    <div className="bg-white dark:bg-slate-800 rounded-xl p-6 md:p-8 w-[95%] md:w-[600px] max-h-[80vh] overflow-y-auto transition-colors duration-300 shadow-xl">

                        <div className="flex justify-between items-center mb-5">

                            <h2 className="text-2xl font-bold text-black dark:text-white">
                                Attachments
                            </h2>

                            <button
                                onClick={() => setShowAttachments(false)}
                                className="text-gray-500 dark:text-gray-300 text-2xl"
                            >
                                ×
                            </button>

                        </div>

                        <div className="space-y-3 mb-6">

                            {attachments.length === 0 ? (

                                <p className="text-gray-500 dark:text-gray-300">
                                    No attachments yet.
                                </p>

                            ) : (

                                attachments.map(file => (

                                    <div
                                        key={file.id}
                                        className="border border-gray-300 dark:border-slate-600 rounded-lg p-4 flex flex-col md:flex-row justify-between md:items-center gap-3 bg-white dark:bg-slate-700 shadow-sm"
                                    >

                                        <div>

                                            <div className="font-semibold text-black dark:text-white">
                                                {file.fileName}
                                            </div>

                                            <div className="text-sm text-gray-500 dark:text-gray-300">
                                                {file.filePath}
                                            </div>

                                        </div>

                                        <button
                                            onClick={() => handleDeleteAttachment(file.id)}
                                            className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded"
                                        >
                                            Delete
                                        </button>

                                    </div>

                                ))

                            )}

                        </div>

                        <input
                            type="text"
                            placeholder="File Name"
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded p-3 mb-3"
                            value={attachment.fileName}
                            onChange={(e) =>
                                setAttachment({
                                    ...attachment,
                                    fileName: e.target.value,
                                })
                            }
                        />

                        <input
                            type="text"
                            placeholder="File Path"
                            className="w-full border border-gray-300 dark:border-slate-600 bg-white dark:bg-slate-700 text-black dark:text-white rounded p-3 mb-4"
                            value={attachment.filePath}
                            onChange={(e) =>
                                setAttachment({
                                    ...attachment,
                                    filePath: e.target.value,
                                })
                            }
                        />

                        <div className="flex justify-end gap-3">

                            <button
                                onClick={() => setShowAttachments(false)}
                                className="px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white rounded"
                            >
                                Close
                            </button>

                            <button
                                onClick={handleAttachmentSave}
                                className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded"
                            >
                                Save
                            </button>

                        </div>

                    </div>

                </div>

            )}

        </MainLayout>

    );

}

export default Tasks;