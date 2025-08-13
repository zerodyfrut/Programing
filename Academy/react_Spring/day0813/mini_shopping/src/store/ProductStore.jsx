import { configureStore } from "@reduxjs/toolkit";
import productReducer from "../slice/ProductSlice";

const ProductStore = configureStore({
    reducer: {
        shopping: productReducer,
    }
})

export default ProductStore;

