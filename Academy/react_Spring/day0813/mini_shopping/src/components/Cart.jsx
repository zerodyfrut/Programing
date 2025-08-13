import { useDispatch, useSelector } from "react-redux";
import { outProduct, putProduct } from "../slice/ProductSlice";

const Cart = () => {

    const dispatch = useDispatch();

    const cartItems = useSelector((state) => state.shopping.cartItems);

    const totalPrice = useSelector((state) => state.shopping.totalPrice);

    return (
        <>
            <div>
                <h2>장바구니</h2>
                {cartItems.length === 0 ? (
                    <p>등록된 상품이 없습니다.</p>
                ) : (
                    <ul>
                        {cartItems.map((product) => (
                            <li key={product.productId}>
                                상품명 : {product.name}<br />
                                1개당 가격 : {product.price}<br />
                                수량 : {product.quantity}<br />
                                해당 제품의 총 가격 : {product.price * product.quantity}<br />
                                <div style={{gap : "10px"}}>
                                    <button onClick={() => dispatch(putProduct(product))}>추가</button>
                                    <button onClick={() => dispatch(outProduct(product))}>삭제</button>
                                </div>
                                <hr />
                            </li>
                        ))}
                    </ul>
                )}
                전체 총 가격 : {totalPrice}<br />
            </div>
        </>
    )
}

export default Cart;