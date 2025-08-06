import React from 'react';
import { useParams,useSearchParams } from 'react-router-dom';

const Product = (props) => {
    // URL paht로 전달된 값 받기(/:이름/...)
    const { productId,f } = useParams();

    // 쿼리 문자열로 전송된 파라미터값 받기
    const [searchParams, setSearchParms] = useSearchParams();

    return (
        <>
            <h3>{productId}번 상품 페이지입니다. code : {searchParams.get('code')} </h3>
            <h3>{f}</h3>
            <ul>
                <li>hash : {location.hash}</li>
                <li>pathname : {location.pathname}</li>
                <li>search : {location.search}</li>
                <li>state : {location.state}</li>
                {/* <li>state : {location.state ? JSON.stringify(location.state) : "없음"}</li> */}
                <li>key : {location.key}</li>
            </ul>
        </>
    );
}

export default Product;