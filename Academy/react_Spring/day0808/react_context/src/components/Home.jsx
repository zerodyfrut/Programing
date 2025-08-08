import { useContext } from "react";
import { ThemeContext } from "./ThemeContext";

const Home = () => {
    const { theme, themeToggle } = useContext(ThemeContext);

    return (
        <>
            <div style={{
                backgroundColor: theme === 'Light' ? '#fff' : '#333',
                color: theme === 'Light' ? '#000' : '#fff'
            }}>
                테마 : {theme}
            </div>
            <button onClick={themeToggle}>테마 변경</button>
        </>
    );

}
export default Home;