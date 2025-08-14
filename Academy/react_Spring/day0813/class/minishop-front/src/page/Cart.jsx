import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router-dom";
import { clearCart, removeFromCart } from "../store/CartSlice";

export default function Cart() {
    const cart = useSelector(state => state.cart);
    const dispatch = useDispatch();

    return (
        <div>
            <h2>장바구니</h2>
            {cart && cart.map(item => (
                <div key={item.id}>
                    <Link to={`/products/${item.id}`}>
                        <img src={`http://localhost:8080${item.imageUrl}`} alt={item.name} width="200" />
                    </Link>
                    <span style={{ fontSize: '50px' }}>{item.name}</span>
                    <button onClick={() => dispatch(removeFromCart(item.id))}>삭제</button>
                </div>
            ))}
            <button onClick={() => dispatch(clearCart())}>장바구니 비우기</button>
        </div>
    );
}
