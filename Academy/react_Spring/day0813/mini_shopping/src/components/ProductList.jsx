import { useEffect, useState, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { deleteProduct, fetchProducts, setPage, searchProducts } from "../slice/ProductSlice";

const ProductList = () => {
    const dispatch = useDispatch();
    const { products, totalPages, loading, error, page } = useSelector((state) => state.shopping);
    const [searchQuery, setSearchQuery] = useState("");
    const debounceTimer = useRef(null);

    useEffect(() => {
        dispatch(fetchProducts(page));
    }, [dispatch, page]);

    const onDelete = (productId) => {
        dispatch(deleteProduct(productId));
    };

    const handleSearch = (query) => {
        if (!query.trim()) {
            dispatch(setPage(0));
            dispatch(fetchProducts(0));
        } else {
            dispatch(setPage(0));
            dispatch(searchProducts(query));
        }
    };

    const handleInputChange = (e) => {
        const value = e.target.value;
        setSearchQuery(value);

        if (debounceTimer.current) clearTimeout(debounceTimer.current);
        debounceTimer.current = setTimeout(() => {
            handleSearch(value);
        }, 500);
    };

    const handleKeyPress = (e) => {
        if (e.key === "Enter") {
            if (debounceTimer.current) clearTimeout(debounceTimer.current);
            handleSearch(searchQuery);
        }
    };

    return (
        <div>
            <h2>상품 목록</h2>

            <div style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
                <input
                    type="text"
                    value={searchQuery}
                    onChange={handleInputChange}
                    onKeyPress={handleKeyPress}
                    placeholder="상품 이름 검색"
                    style={{ flex: 1, padding: "5px" }}
                />
                <button onClick={() => handleSearch(searchQuery)} style={{ padding: "5px 10px" }}>
                    검색
                </button>
            </div>

            {loading && <p>상품목록을 읽는중..</p>}
            {error && <p>{error}</p>}

            {!loading && !error && (
                <>
                    {products.length === 0 ? (
                        <p>등록된 상품이 없습니다.</p>
                    ) : (
                        <ul>
                            {products.map((product) => (
                                <li key={product.productId} style={{ marginBottom: 20 }}>
                                    <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
                                        <img
                                            src={product.image ? `http://localhost:8080${product.image}` : ""}
                                            alt={product.name}
                                            style={{ width: 100, height: 100, objectFit: "cover" }}
                                        />

                                        <div>
                                            <Link
                                                to={`/product/${product.productId}`}
                                                style={{ fontWeight: "bold", fontSize: 16 }}
                                            >
                                                {product.name}
                                            </Link>
                                            <p>가격: {product.price}</p>

                                            <div style={{ display: "flex", gap: "10px", marginTop: 5 }}>
                                                <button onClick={() => onDelete(product.productId)}>
                                                    제품삭제
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <hr style={{ marginTop: 10 }} />
                                </li>
                            ))}
                        </ul>
                    )}

                    {/* 페이지네이션 */}
                    <div style={{ marginTop: 20 }}>
                        <button
                            onClick={() => dispatch(setPage(page - 1))}
                            disabled={page === 0}
                        >
                            이전
                        </button>

                        {Array.from({ length: totalPages }, (_, idx) => (
                            <button
                                key={idx}
                                onClick={() => dispatch(setPage(idx))}
                                style={{
                                    fontWeight: idx === page ? "bold" : "normal",
                                    margin: "0 5px",
                                }}
                            >
                                {idx + 1}
                            </button>
                        ))}

                        <button
                            onClick={() => dispatch(setPage(page + 1))}
                            disabled={page + 1 === totalPages}
                        >
                            다음
                        </button>
                    </div>
                </>
            )}
        </div>
    );
};

export default ProductList;
