import { useEffect, useRef, useState } from "react"

const HookCanvas = () => {
    const paper = useRef();
    const [pen, setPen] = useState();//2d context   
    // const pen=useRef();

    useEffect(() => {
        setPen(paper.current.getContext("2d"));//canves DOM
    }, []);

    return (
        <>
            <canvas onClick={(e) => {
                pen.fillRect(
                    e.nativeEvent.offsetX - 25, //x좌표
                    e.nativeEvent.offsetY - 25, //y좌표
                    50, 50 //크기 50x50
                );
            }}
                ref={paper} width={300} height={300}
                style={{ border: "black solid 2px" }}
            />
           
        </>
    );
};

export default HookCanvas