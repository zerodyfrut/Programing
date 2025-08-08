import { createContext, useContext } from "react"
import { ThemeContext } from "./ThemeContext";

const divsContext = createContext();

export const Sub1 = () => {
    const divs = { color: 'red' };

    return (
        <>
            <div style={{
                border: '10px solid green'}}>
                Hello world!!
                <divsContext.Provider value={divs}>
                    <Sub2 />
                </divsContext.Provider>
            </div>
            <Sub3 />
        </>
    )
}

const Sub2 = () => {
    const { theme } = useContext(ThemeContext);
    const divs = useContext(divsContext);
    return (
        <>
            <div style={{
                divs, backgroundColor: theme === 'Dark' ? '#fff' : '#333',
                color: theme === 'Light' ? 'red' : '#333'
            }}>
                Hello world!!
            </div >
            <div theme></div>
        </>
    )
}

const Sub3 = () => {
    const divs = useContext(divsContext);
    return (
        <div style={divs} >
            Hello world!!
        </div>
    )
}

