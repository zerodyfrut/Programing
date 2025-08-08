import { createContext, useContext } from "react";
import React from "react";
import { ThemeContext } from "./ThemeContext";

//1. context 생성
//const UserContext = React.createContext({ name: 'kim', role: '프로그래머' });
const UserContext = React.createContext();

//2. 부모 컴포넌트(값 제공)
export const Parent = () => {
    const user = { name: 'abc', role: '123' }

    return (
        <UserContext.Provider value={user}>
            <Child />
        </UserContext.Provider>

    );
    //<Child/>
}
//3. 자식 컴포넌트(값 소비)
const Child = () => {
    const user = useContext(UserContext);
    const { theme, themeToggle } = useContext(ThemeContext);

    return (
        <div style={{
            backgroundColor: theme === 'Light' ? '#fff' : '#333',
            color: theme === 'Light' ? '#000' : '#fff'
        }}>안녕하세요.{user.name}님! 역할은 {user.role}입니다.</div>
    )
}