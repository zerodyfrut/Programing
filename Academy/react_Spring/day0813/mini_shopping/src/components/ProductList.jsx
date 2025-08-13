import axios from "axios";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { deleteProduct, fetchProducts, setPage } from "../slice/ProductSlice";

const ProductList = () => {
    const dispatch = useDispatch();
    const { products, totalPages, loading, error } = useSelector((state) => state.shopping);
    const page = useSelector(state => state.shopping.page);

    useEffect(() => {
        dispatch(fetchProducts(page));
    }, [dispatch, page])

    const onDelete = (productId) => {
        dispatch(deleteProduct(productId));
    }

    if (loading) return <p>상품목록을 읽는중..</p>
    if (error) return <p>{error}</p>


    return (
        <>
            <div>
                <h2>상품 목록</h2>
                {products.length === 0 ? (
                    <p>등록된 상품이 없습니다.</p>
                ) : (
                    <ul>
                        {products.map((product) => (
                            <li key={product.productId}>
                                <Link to={`/product/${product.productId}`}>{product.name}</Link><br />
                                {/* 상품명 : {product.name}<br /> */}
                                가격 : {product.price}<br />
                                {/* 리스트에서 상세설명을 보여줄 필욘 없겠지. */}

                                <button onClick={() => onDelete(product.productId)}>제품삭제</button>
                                <hr />
                            </li>
                        ))}
                    </ul>
                )}
            </div>
            <div>
                <button
                    onClick={() => {
                        dispatch(setPage(page - 1));
                        dispatch(fetchProducts(page - 1));
                    }}
                    disabled={page === 0}>
                    이전</button>

                {Array.from({ length: totalPages }, (_, idx) => (
                    <button
                        key={idx}
                        onClick={() => {
                            dispatch(setPage(idx));
                            dispatch(fetchProducts(idx));
                        }}
                        style={{
                            fontWeight: idx === page ? "bold" : "normal",
                            margin: "0 5px",
                        }}
                    >
                        {idx + 1}
                    </button>
                ))}
                <button
                    onClick={() => {
                        dispatch(setPage(page + 1));
                        dispatch(fetchProducts(page + 1));
                    }}
                    disabled={page + 1 === totalPages}>
                    다음</button>

            </div >
        </>
    )
}

export default ProductList;