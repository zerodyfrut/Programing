import axios from "axios";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom"
import { addToCart } from "../store/CartSlice";

export default function ProductDetail() {
    const { id } = useParams();
    const dispatch = useDispatch();
    const [product, setProduct] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/products/${id}`).then(res => setProduct(res.data));
    }, [id])

    if (!product) return <div>로딩중..</div>

    return (
        <div>
            <img src={`http://localhost:8080${product.imageUrl}`} alt={product.name} width="200" />
            <h2>{product.name}</h2>
            <p>{product.description}</p>
            <button onClick={() => dispatch(addToCart(product))}>장바구니</button>
        </div>
    );
}