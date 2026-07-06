import { createContext, useContext, useEffect, useState } from "react";

const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {

    const [theme, setTheme] = useState("LIGHT");

    useEffect(() => {

        const user = JSON.parse(localStorage.getItem("user"));

        if (user?.theme) {
            setTheme(user.theme);
        }

    }, []);

    useEffect(() => {

        if (theme === "DARK") {

            document.documentElement.classList.add("dark");

        } else {

            document.documentElement.classList.remove("dark");

        }

    }, [theme]);

    return (

        <ThemeContext.Provider
            value={{ theme, setTheme }}
        >
            {children}
        </ThemeContext.Provider>

    );

};

export const useTheme = () => useContext(ThemeContext);