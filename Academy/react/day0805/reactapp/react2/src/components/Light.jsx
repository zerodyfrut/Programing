//버튼-클릭 이벤트
//light의 값이 on이면 off로, off면 으로 변경하는 함수로 리랜더링
//on 이면 버튼 text가 '끄기', 아니라면 '켜기'
import { useState } from "react";
//부모 리렌더링 -> 자식도 같이 리렌더링
//해당 컴포넌트의 state 값은 그대로 유지
//렌더링은 화면을 다시 그리는 것이지, 데이터초기화는 아님.

// class
const Light = () => {
    const[light, setLight] = useState("OFF");
    console.log("light : "+light)

    return (
        <>
            <button onClick={()=>setLight(light === "ON" ? "OFF" : "ON")}>
            {light === "ON" ? "끄기" : "켜기"}
        </button>

        </>
    )

}



// const Light = () => {

//     const [state, setState] = useState("on");

//     const change = () => {
//         const a= state=="on"?"off":"on"
//         setState(a)
//     }

//     return (
//         <>
//         <br/>
//             <button onClick={change}>
//                 {state=="on"?"끄기":"켜기"}
//             </button>
            
//         </>
//     );
// }
export default Light;