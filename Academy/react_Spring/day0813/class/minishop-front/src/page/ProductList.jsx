import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function ProductList() {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/products?page=0&size=10`)
            .then(res => setProducts(res.data.content))
            .then(console.log(products));
    }, [])

    useEffect(() => {
        axios.get(`http://localhost:8080/api/products?page=${page}&size=10`)
            .then(res => setProducts(res.data.content));
    }, [page])

    return (
        <div>
            <h2>전체 상품 리스트</h2>
            {products && products.map(p => (
                <div key={p.id}>
                    <Link to={`/products/${p.id}`}>
                        <img src={`http://localhost:8080${p.imageUrl}`} alt={p.name} width="100" />
                    </Link>
                    <h3>{p.name}</h3>
                    <p>{p.description}</p>
                </div>
            ))}
            <button onClick={() => setPage(prev => Math.max(prev - 1, 0))}>이전</button>
            <button onClick={() => setPage(prev => prev + 1)}>다음</button>
        </div>
    );
}