import { useEffect, useState } from "react";

const Timer = () => {

    const [seconds, setSeconds] = useState(0);
    //타이머 시작 &정리
    useEffect(() => {
        console.log("타이머 시작(마운트)"); //시작할때 한번만 log
        const interval= setInterval(()=>{setSeconds((prev)=>{return prev+1})},1000);

        return ()=>{
            clearInterval(interval);
            console.log("타이머 종료(언마운트)");
        }
    }, []);

    //seconds 변경시마다 로그를 찍어주기
    //seconds 변경시마다 렌더링, 수행
    useEffect(() => {
        console.log(`현재 초 : ${seconds}`);
    }, [seconds]);

    return (
        <>
            <h2>타이머 : {seconds}초</h2>
        </>
    );
}

export default Timer;