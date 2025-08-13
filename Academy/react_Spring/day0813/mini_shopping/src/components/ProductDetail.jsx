import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { putProduct } from "../slice/ProductSlice";
import { useDispatch } from "react-redux";

const ProductDetail = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState(null);
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(true);
    const dispatch=useDispatch();

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const res = await axios.get(`http://localhost:8080/api/shopping/${productId}`);
                setProduct(res.data);
            } catch (e) {
                setErr("상품을 읽어오는데 실패했습니다.");
            } finally {
                setLoading(false);
            }
        }
        fetchProduct();
    }, [productId])

    if (loading) return <p>상품 정보 불러오는중..</p>
    if (err) return <p>{err}</p>
    if (!product) return <p>상품을 찾을 수 없습니다.</p>

    return (
        <div>
            <h2> {product.name}</h2>
            <img src={`http://localhost:8080${product.image}`}
                alt={product.name} style={{ width: 200, height: 200, objectFit: "cover", marginTop: 10 }} /><br/>
            가격 : {product.price}<br />
            상세설명 : {product.description}<br />
            <button onClick={()=>dispatch(putProduct(product))}>장바구니에 추가</button>
        </div>
    )
}
export default ProductDetail;