import React from 'react';
import { Link } from 'react-router-dom';


const Main = (props) => {
    return (
        <>
            <h3>안녕하세요. 메인페이지 입니다.</h3>
            <ul>
                {/* <Link to="/product/1" state={{ name: "노트북", price: 3000 }}> */}
                <Link to="/product/1/apple?code=test1">
                {/* 페이지 이동을 위한 Link 태그 */}
                    <li>1번상품</li></Link>
                <Link to="/product/2/peach?code=test2">
                    <li>2번상품</li></Link>
            </ul>
        </>
    );
}
export default Main;