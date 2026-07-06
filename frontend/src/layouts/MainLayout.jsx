import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";

function MainLayout({ children }) {

    return (

        <div className="flex flex-col md:flex-row min-h-screen bg-slate-100 dark:bg-slate-900 transition-colors duration-300">

            <Sidebar />

            <div className="flex-1 flex flex-col">

                <Navbar />

                <main className="flex-1 p-4 md:p-8 text-black dark:text-white transition-colors duration-300 overflow-x-auto">

                    {children}

                </main>

            </div>

        </div>

    );

}

export default MainLayout;