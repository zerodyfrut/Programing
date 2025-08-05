
// state에 여러 속성을 가지고 있는 객체를 저장
// color: 속성, 글씨 색상, bgcolor: 배경색
// input 태그에 입력한 값으로 글씨의 색과 배경색 변경

import { useState } from "react";


function isValidCssColor(color) {
    const s = new Option().style;//<option> 태그생성후 sytle 접근
    s.color = color //color 값이 유효하면 s.color에 저장, 아니면 빈 문자열
    // console.log(s.color);
    return s.color !== ''; 
    //굳이 !=="" 없어도 무방, 없으면 color 값을 리턴, 위에처럼 쓰면 t/f 리턴

}

const ChangeColor = () => {
    const [cssh1, setCssh1] = useState({ color: "black", backgroundColor: "white" });
    const [color, setColor] = useState("black");
    const [bgcolor, setBgcolor] = useState("white");

    const changeColor = () => {
        console.log(isValidCssColor(color));
        if (isValidCssColor(color) && isValidCssColor(bgcolor)) {
            setCssh1({ color: color, backgroundColor: bgcolor });
            
        } else {
            alert("올바른 색상이 아님")
        }
    }

    return (
        <>
            <h1 style={cssh1}>h1 tag</h1>
            배경색 : <input value={bgcolor} onChange={(e) => setBgcolor(e.target.value)} /><br />
            글자색 : <input value={color} onChange={(e) => setColor(e.target.value)} /><br />

            <button onClick={changeColor}>h1 색상 변경</button>
        </>
    );
}

export default ChangeColor;