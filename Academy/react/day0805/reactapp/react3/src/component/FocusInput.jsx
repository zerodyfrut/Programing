import { useRef } from "react";

const FocustInput=()=>{
    const inputRef=useRef(null);//DOM 참조객체 생성

    const handleClick=()=>{

        inputRef.current.value="";
        inputRef.current.focus();//DOM에 직접 접근
    }
    //ref속성을 이용해 useRef()객체와 연결
    //자동으로 DOM요소를 current에 집어 넣어 주는 것.
    return (
        <div>
            <input ref={inputRef} placeholder="입력"/>
            <button onClick={handleClick}>입력창에 포커스</button>
        </div>
    );

}
export default FocustInput;