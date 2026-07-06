import {
    Chart as ChartJS,
    ArcElement,
    Tooltip,
    Legend,
    CategoryScale,
    LinearScale,
    BarElement,
} from "chart.js";

import { Pie, Bar } from "react-chartjs-2";

ChartJS.register(
    ArcElement,
    Tooltip,
    Legend,
    CategoryScale,
    LinearScale,
    BarElement
);

function DashboardCharts({ dashboard }) {

    const projectData = {
        labels: ["Projects"],
        datasets: [
            {
                label: "Projects",
                data: [dashboard?.totalProjects ?? 0],
                backgroundColor: ["#3B82F6"],
            },
        ],
    };

    const taskData = {
        labels: [
            "Tasks",
            "Members",
            "Notifications",
        ],
        datasets: [
            {
                label: "Overview",
                data: [
                    dashboard?.totalTasks ?? 0,
                    dashboard?.totalMembers ?? 0,
                    dashboard?.totalNotifications ?? 0,
                ],
                backgroundColor: [
                    "#22C55E",
                    "#A855F7",
                    "#EF4444",
                ],
            },
        ],
    };

    return (

        <div className="grid md:grid-cols-2 gap-8 mt-10">

            <div className="bg-white dark:bg-slate-800 rounded-xl shadow p-6">

                <h2 className="text-xl font-bold mb-6 text-black dark:text-white">
                    Projects Overview
                </h2>

                <Pie data={projectData} />

            </div>

            <div className="bg-white dark:bg-slate-800 rounded-xl shadow p-6">

                <h2 className="text-xl font-bold mb-6 text-black dark:text-white">
                    Team Overview
                </h2>

                <Bar data={taskData} />

            </div>

        </div>

    );

}

export default DashboardCharts;