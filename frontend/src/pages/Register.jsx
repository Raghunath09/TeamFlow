import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../services/authService";
import toast from "react-hot-toast";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        fullName: "",
        email: "",
        password: "",
        confirmPassword: "",
    });

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {

            toast.error("Passwords do not match");

            return;

        }

        try {

            await register({
                fullName: formData.fullName,
                email: formData.email,
                password: formData.password,
            });

            toast.success("Registration Successful");

            navigate("/");

        } catch (error) {

            console.error(error);

            toast.error(
                error.response?.data?.message || "Registration Failed"
            );

        }

    };

    return (

        <div className="min-h-screen bg-slate-100 dark:bg-slate-900 flex items-center justify-center transition-colors duration-300">

            <div className="bg-white dark:bg-slate-800 p-8 rounded-xl shadow-lg w-full max-w-md transition-colors duration-300">

                <h1 className="text-3xl font-bold text-center text-blue-600 mb-2">

                    TeamFlow

                </h1>

                <p className="text-center text-gray-500 dark:text-gray-300 mb-8">

                    Create your account

                </p>

                <form onSubmit={handleSubmit} className="space-y-5">

                    <div>

                        <label className="block mb-2 font-medium text-black dark:text-white">

                            Full Name

                        </label>

                        <input
                            type="text"
                            name="fullName"
                            value={formData.fullName}
                            onChange={handleChange}
                            required
                            className="w-full border rounded-lg p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                    </div>

                    <div>

                        <label className="block mb-2 font-medium text-black dark:text-white">

                            Email

                        </label>

                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                            className="w-full border rounded-lg p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                    </div>

                    <div>

                        <label className="block mb-2 font-medium text-black dark:text-white">

                            Password

                        </label>

                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                            className="w-full border rounded-lg p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                    </div>

                    <div>

                        <label className="block mb-2 font-medium text-black dark:text-white">

                            Confirm Password

                        </label>

                        <input
                            type="password"
                            name="confirmPassword"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            required
                            className="w-full border rounded-lg p-3 bg-white dark:bg-slate-700 text-black dark:text-white border-gray-300 dark:border-slate-600 outline-none focus:ring-2 focus:ring-blue-500"
                        />

                    </div>

                    <button
                        type="submit"
                        className="w-full bg-blue-600 hover:bg-blue-700 text-white p-3 rounded-lg transition"
                    >

                        Register

                    </button>

                </form>

                <p className="text-center mt-6 text-gray-600 dark:text-gray-300">

                    Already have an account?{" "}

                    <Link
                        to="/"
                        className="text-blue-600 hover:underline font-semibold"
                    >
                        Login
                    </Link>

                </p>

            </div>

        </div>

    );

}

export default Register;