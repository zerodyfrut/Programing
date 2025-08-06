import { useEffect, useState } from "react";

const PopupMenu = () => {

    const [popupCSS, setPopupCSS] = useState({
        position: "fixed", //브라우저 창을 기준으로
        top: -100,//안보이는 위치에
        left: -100,
        opacity: 0 //안보이는 위치인데 0으로 둘필요있나? 굳이 opacity 정의할 이유가?
                    //-> 그냥 안정성, 꼭할 필요는 없음
    })

    const summonPopup = (e) => {
        e.preventDefault();// 기존 기능 막기
    }

    const summonMyPopup = (e) => {
         if (e.button === 2) { //클릭 버튼 값이 0:좌클릭, 1: 휠, 2:우클릭 
            setPopupCSS({
                ...popupCSS,
                top: e.clientY,
                left: e.clientX,
                opacity: 1
            });
            setTimeout(() => {//3초가 지나면 해당 팝업이 제거됨(초기값으로 변함)
                setPopupCSS({
                    ...popupCSS,
                    top: -100,
                    left: -100,
                    opacity: 0
                });
            }, 3000);
         }
    }

    useEffect(() => {
        document.addEventListener("contextmenu", summonPopup); //contextmenu : 우클릭 이벤트 발생
        document.addEventListener("mouseup", summonMyPopup);
        return () => {
            document.removeEventListener("contextmenu", summonPopup);
            document.removeEventListener("mouseup", summonMyPopup);
        };
    }, []);

    return (
        <>
            <table border={1} style={popupCSS}>
                <tr>
                    <td>
                        <a href="https://www.naver.com">네이버로</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="https://www.google.com">구글로</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="https://www.daum.net">다음으로</a>
                    </td>
                </tr>
            </table>
        </>
    );
}

export default PopupMenu;