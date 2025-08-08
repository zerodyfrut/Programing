import { createContext, useState } from "react";

export const ThemeContext = createContext();
export const ThemeProvider = ({ children }) => {

    const [theme, setTheme] = useState('Light');

    const themeToggle = () =>
        setTheme((prev) => (prev === 'Light' ? 'Dark' : 'Light'));

    return (
        <ThemeContext.Provider value={{ theme, themeToggle }}>
            {children}
        </ThemeContext.Provider>
    )
}

// export default ThmeContext;