import { useState } from "react";

const Parent = () => {
    const [count, setCount] = useState(0);

    return (
        <div>
            <h2>부모 count: {count}</h2>
            <button onClick={() => { setCount(count + 1) }}>부모 +1</button>
            <Child value={count} />
        </div>
    );
}

//부모 상태 변경 → count 변경 → Child에 전달된 props 변경 → 자식 컴포넌트 리렌더링
function Child({ value }) {
    console.log("자식 렌더링");
    return (
        <>
            <h3>전달 받은 값 : {value}</h3>
        </>
    );
}

export default Parent;