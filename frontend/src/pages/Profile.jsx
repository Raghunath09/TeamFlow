import { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import { getProfile, updateProfile } from "../services/userService";
import { useTheme } from "../context/ThemeContext";
import Loading from "../components/Loading";
import toast from "react-hot-toast";

function Profile() {

    const user = JSON.parse(localStorage.getItem("user"));

    const [loading, setLoading] = useState(true);

    const { setTheme } = useTheme();

    const [profile, setProfile] = useState({
        fullName: "",
        email: "",
        role: "",
        theme: "LIGHT",
    });

    useEffect(() => {
        fetchProfile();
    }, []);

    const fetchProfile = async () => {

        try {

            const response = await getProfile(user.id);

            setProfile(response.data.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const handleUpdate = async () => {

        try {

            await updateProfile(user.id, {
                fullName: profile.fullName,
                theme: profile.theme,
            });

            const updatedUser = {
                ...user,
                theme: profile.theme,
            };

            localStorage.setItem(
                "user",
                JSON.stringify(updatedUser)
            );

            setTheme(profile.theme);

            toast.success("Profile Updated Successfully");

            fetchProfile();

        } catch (error) {

            toast.error("Failed to update profile");
            console.error(error);

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

            <div className="max-w-2xl mx-auto bg-white dark:bg-slate-800 rounded-xl shadow p-8 transition-colors duration-300">

                <h1 className="text-3xl font-bold mb-8 text-black dark:text-white">
                    Profile
                </h1>

                <div className="space-y-5">

                    <input
                        type="text"
                        value={profile.fullName}
                        onChange={(e) =>
                            setProfile({
                                ...profile,
                                fullName: e.target.value,
                            })
                        }
                        className="w-full border rounded p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600"
                    />

                    <input
                        type="email"
                        value={profile.email}
                        disabled
                        className="w-full border rounded p-3 bg-gray-100 dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600"
                    />

                    <input
                        type="text"
                        value={profile.role}
                        disabled
                        className="w-full border rounded p-3 bg-gray-100 dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600"
                    />

                    <select
                        value={profile.theme}
                        onChange={(e) =>
                            setProfile({
                                ...profile,
                                theme: e.target.value,
                            })
                        }
                        className="w-full border rounded p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600"
                    >
                        <option value="LIGHT">LIGHT</option>
                        <option value="DARK">DARK</option>
                    </select>

                    <button
                        onClick={handleUpdate}
                        className="bg-blue-600 text-white px-6 py-3 rounded"
                    >
                        Update Profile
                    </button>

                </div>

            </div>

        </MainLayout>
    );

}

export default Profile;