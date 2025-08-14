//store에 등록
//slice별로 state가 분리되어 있지만 하나의 store에서 관리됨

import { configureStore } from "@reduxjs/toolkit";
import cartReducer from './store/cartSlice';

const store = configureStore({
    reducer: {
        cart: cartReducer
    }
})

export default store;