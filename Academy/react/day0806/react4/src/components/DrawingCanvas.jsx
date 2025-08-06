import { useEffect, useRef } from "react";

const DrawingCanvas = () => {
    const canvasRef = useRef(null);
    const ctxRef = useRef(null);
    const isDrawing = useRef(false);//상태 저장용(리렌더링 필요 x)

    useEffect(() => {
        const canvas = canvasRef.current;
        canvas.width = 500;
        canvas.height = 500;

        canvas.style.border = "2px solid black"

        const ctx = canvas.getContext("2d");
        ctx.lineCap = "round"; // 끝을 둥글게. 펜촉, butt, square
        ctx.lineWidth = 3;
        ctx.strokeStyle = "blue";

        ctxRef.current = ctx;
    }, []);

    const handleMouseDown = (e) => {
        isDrawing.current = true;
        const { offsetX, offsetY } = e.nativeEvent;
        ctxRef.current.beginPath(); // 새로운 그림의 시작
        ctxRef.current.moveTo(offsetX, offsetY); // pen을 해당 위치로 이동
    };

    const handleMouseMove = (e) => {
        if (!isDrawing.current) return;
        const { offsetX, offsetY } = e.nativeEvent;
        ctxRef.current.lineTo(offsetX, offsetY); //선을 그음
        ctxRef.current.stroke(); //선을 출력
    };

    const handleMouseUp = () => {
        isDrawing.current = false;
        ctxRef.current.closePath(); //선을 끝냄
    };

    return (
        <canvas
            ref={canvasRef}
            onMouseDown={handleMouseDown}
            onMouseMove={handleMouseMove}
            onMouseUp={handleMouseUp}
            onMouseLeave={handleMouseUp}//캔버스를 벗어날때도 종료
        />
    );
}

export default DrawingCanvas;