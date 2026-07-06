import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
    getNotifications,
} from "../services/notificationService";

function Navbar() {

    const user = JSON.parse(localStorage.getItem("user"));

    const navigate = useNavigate();

    const [notifications, setNotifications] = useState([]);

    const [showNotifications, setShowNotifications] = useState(false);

    useEffect(() => {

        if (user?.id) {
            fetchNotifications();
        }

    }, []);

    const fetchNotifications = async () => {

        try {

            const response = await getNotifications(user.id);

            setNotifications(response.data.data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleLogout = () => {

        localStorage.removeItem("token");
        localStorage.removeItem("user");

        navigate("/");

    };

    return (

        <header className="bg-white dark:bg-slate-800 shadow-md px-4 md:px-8 py-4 flex flex-col md:flex-row justify-between items-center gap-4 transition-colors duration-300">

            <h2 className="text-2xl font-bold text-black dark:text-white">
                Dashboard
            </h2>

            <div className="flex items-center gap-4 relative">

                <button
                    onClick={() => setShowNotifications(!showNotifications)}
                    className="relative text-2xl hover:scale-110 transition"
                >
                    🔔

                    {notifications.length > 0 && (

                        <span className="absolute -top-2 -right-2 bg-red-600 text-white rounded-full min-w-[22px] h-[22px] flex items-center justify-center text-xs font-bold">

                            {notifications.length}

                        </span>

                    )}

                </button>

                {showNotifications && (

                    <div className="absolute top-12 right-0 w-80 bg-white dark:bg-slate-800 shadow-xl rounded-xl border border-gray-200 dark:border-slate-700 z-50">

                        <div className="p-4 border-b dark:border-slate-700">

                            <h3 className="font-bold text-lg text-black dark:text-white">
                                Notifications
                            </h3>

                        </div>

                        <div className="max-h-80 overflow-y-auto">

                            {notifications.length === 0 ? (

                                <p className="p-4 text-gray-500 dark:text-gray-300">
                                    No Notifications
                                </p>

                            ) : (

                                notifications.map((notification) => (

                                    <div
                                        key={notification.id}
                                        className="p-4 border-b dark:border-slate-700 hover:bg-gray-100 dark:hover:bg-slate-700 transition"
                                    >

                                        <div className="font-semibold text-black dark:text-white">

                                            {notification.title}

                                        </div>

                                        <div className="text-sm text-gray-600 dark:text-gray-300 mt-1">

                                            {notification.message}

                                        </div>

                                        <div className="text-xs text-gray-400 mt-2">

                                            {notification.createdAt}

                                        </div>

                                    </div>

                                ))

                            )}

                        </div>

                    </div>

                )}

                <div className="hidden md:block h-8 w-px bg-gray-300 dark:bg-slate-600"></div>

                <div className="text-center md:text-right">

                    <p className="font-semibold text-black dark:text-white break-all">

                        {user?.email}

                    </p>

                </div>

                <button
                    onClick={handleLogout}
                    className="bg-red-600 hover:bg-red-700 text-white px-5 py-2 rounded-lg transition duration-200 shadow"
                >
                    Logout
                </button>

            </div>

        </header>

    );

}

export default Navbar;