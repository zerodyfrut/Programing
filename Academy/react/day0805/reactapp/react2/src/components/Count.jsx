import { useState } from "react";
import Light from "./Light";

const Count=()=>{
    const [count,setState]=useState(0);

    return (
        <>
        <h1>{count}</h1>
        {/* state 값은 직접변경 하지 않는다. */}
        <button onClick={()=>{setState(count-1)}}> - </button>
        <button onClick={()=>{setState(count+1)}}> + </button>
        <Light/>
        </>
    );
    
}
export default Count;