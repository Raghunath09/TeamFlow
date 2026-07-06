import { Link, useLocation } from "react-router-dom";

function Sidebar() {

    const location = useLocation();

    const menuItems = [
        { name: "Dashboard", path: "/dashboard", icon: "🏠" },
        { name: "Projects", path: "/projects", icon: "📁" },
        { name: "Tasks", path: "/tasks", icon: "✅" },
        { name: "Profile", path: "/profile", icon: "👤" },
    ];

    return (

        <aside className="w-full md:w-64 bg-slate-900 dark:bg-slate-950 text-white md:min-h-screen transition-colors duration-300 shadow-lg">

            <div className="p-6">

                <h1 className="text-3xl font-bold mb-8 text-center">
                    TeamFlow
                </h1>

                <nav className="flex flex-col md:block space-y-2">

                    {menuItems.map((item) => (

                        <Link
                            key={item.path}
                            to={item.path}
                            className={`flex items-center gap-3 px-4 py-3 rounded-lg transition-all duration-200
                            ${
                                location.pathname === item.path
                                    ? "bg-blue-600 text-white"
                                    : "hover:bg-slate-800 dark:hover:bg-slate-700"
                            }`}
                        >
                            <span className="text-lg">
                                {item.icon}
                            </span>

                            <span className="font-medium">
                                {item.name}
                            </span>

                        </Link>

                    ))}

                </nav>

            </div>

        </aside>

    );

}

export default Sidebar;