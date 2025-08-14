import { useSelector, useDispatch } from "react-redux";
import { clearCart, outProduct, putProduct } from "../slice/ProductSlice";

const Cart = () => {
    const dispatch = useDispatch();
    const { cartItems, totalPrice } = useSelector(state => state.shopping);

    return (
        <div>
            <h2>장바구니</h2>
            {cartItems.length === 0 ? (
                <p>장바구니가 비어있습니다.</p>
            ) : (
                <>
                    <ul>
                        {cartItems.map(item => (
                            <li key={item.productId} style={{ marginBottom: 10 }}>
                                {item.name} - {item.quantity}개 - {item.price * item.quantity}원
                                <button onClick={() => dispatch(outProduct(item))} style={{ marginLeft: 5 }}>-1</button>
                                <button onClick={() => dispatch(putProduct(item))} style={{ marginLeft: 5 }}>+1</button>
                            </li>
                        ))}
                    </ul>
                    <p>총 금액: {totalPrice}원</p>
                    <button
                        onClick={() => dispatch(clearCart())}
                        style={{ backgroundColor: "red", color: "white", padding: "5px 10px", marginTop: 10 }}
                    >
                        장바구니 비우기
                    </button>
                </>
            )}
        </div>
    );
};

export default Cart;
