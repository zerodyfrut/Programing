import { createSlice } from "@reduxjs/toolkit";

const CartSlice = createSlice({
    name: 'cart',
    initialState: [],
    reducers: {
        addToCart: (state, action) => {
            const exists = state.find(item => item.id === action.payload.id);
            if (exists) {
                alert("이미 추가된 상품입니다.");
            } else {
                alert("장바구니 추가 완료");
                state.push(action.payload);
            }
        },

        removeFromCart:(state,action)=>{
            return state.filter(item=>item.id!==action.payload);//이건 id가 아니네, id로 받나?
        },

        clearCart : ()=>[]
    }
})

export const {addToCart,removeFromCart, clearCart}=CartSlice.actions;
export default CartSlice.reducer;