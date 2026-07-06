import MainLayout from "../layouts/MainLayout";
import { useEffect, useState } from "react";
import { getDashboard } from "../services/dashboardService";
import DashboardCharts from "../components/DashboardCharts";
import Loading from "../components/Loading";

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchDashboard();
    }, []);

    const fetchDashboard = async () => {

        try {

            const response = await getDashboard();

            setDashboard(response.data.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

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

            <div className="mb-8">

                <h1 className="text-3xl md:text-4xl font-bold text-black dark:text-white">

                    Welcome back 👋

                </h1>

                <p className="text-gray-500 dark:text-gray-300 mt-2 text-sm md:text-base">

                    Manage your projects efficiently from one place.

                </p>

            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-6">

                <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 p-6">

                    <h2 className="text-gray-500 dark:text-gray-300 font-medium">
                        Projects
                    </h2>

                    <p className="text-4xl font-bold text-blue-600 mt-4">

                        {dashboard?.totalProjects ?? 0}

                    </p>

                </div>

                <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 p-6">

                    <h2 className="text-gray-500 dark:text-gray-300 font-medium">
                        Tasks
                    </h2>

                    <p className="text-4xl font-bold text-green-600 mt-4">

                        {dashboard?.totalTasks ?? 0}

                    </p>

                </div>

                <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 p-6">

                    <h2 className="text-gray-500 dark:text-gray-300 font-medium">
                        Members
                    </h2>

                    <p className="text-4xl font-bold text-purple-600 mt-4">

                        {dashboard?.totalMembers ?? 0}

                    </p>

                </div>

                <div className="bg-white dark:bg-slate-800 rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 p-6">

                    <h2 className="text-gray-500 dark:text-gray-300 font-medium">
                        Notifications
                    </h2>

                    <p className="text-4xl font-bold text-red-600 mt-4">

                        {dashboard?.totalNotifications ?? 0}

                    </p>

                </div>

            </div>

            <DashboardCharts dashboard={dashboard} />

        </MainLayout>

    );

}

export default Dashboard;