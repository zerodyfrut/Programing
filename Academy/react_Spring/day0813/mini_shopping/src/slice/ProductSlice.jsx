import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { toast } from 'react-toastify';


//상품 목록조회
export const fetchProducts = createAsyncThunk(
    "shopping/fetProducts",
    async (page, thunkApi) => {
        try {
            const res = await axios.get(`http://localhost:8080/api/shopping?page=${page}`);
            return res.data;
        } catch (err) {
            return thunkApi.rejectWithValue("상품목록을 불러오는데 실패하였습니다.");
        }
    }
)

// 상품 삭제
export const deleteProduct = createAsyncThunk(
    "shopping/deleteProduct",
    async (productId, thunkAPI) => {
        try {
            await axios.delete(`http://localhost:8080/api/shopping/${productId}`);
            toast.success("상품이 삭제되었습니다.");
            return productId;
        } catch (err) {
            toast.error("상품 삭제에 실패했습니다.");
            return thunkAPI.rejectWithValue(err.response?.data || "삭제 실패");
        }
    }
);

export const searchProducts = createAsyncThunk(
    "shopping/searchProducts",
    async (query, thunkAPI) => {
        try {
            const encodedQuery = encodeURIComponent(query);
            const res = await axios.get(`http://localhost:8080/api/shopping/search?query=${encodedQuery}`);
            return res.data;
        } catch (err) {
            return thunkAPI.rejectWithValue("검색 중 오류가 발생했습니다.");
        }
    }
);




const initialState = {
    cartItems: [],
    totalPrice: 0,
    products: [],
    page: 0,
    totalPages: 0,
    loading: false,
    error: null,
};

const ProductSlice = createSlice({
    name: 'shopping',
    initialState,
    reducers: {
        setPage: (state, action) => {
            state.page = action.payload;
        },
        putProduct: (state, action) => {
            const item = action.payload;

            // 상품이 아직 목록에 있는지 체크
            const existsInList = state.products.find(p => p.productId === item.productId);
            if (!existsInList) {
                toast.error(`${item.name}은(는) 삭제된 상품입니다.`);
                return;
            }

            const existItem = state.cartItems.find(i => i.productId === item.productId);
            if (existItem) {
                existItem.quantity = (existItem.quantity || 0) + 1;
                toast.success(`${item.name}이/가 장바구니에 1개 추가되었습니다.`);
            } else {
                state.cartItems.push({ ...item, quantity: 1 });
                toast.success(`${item.name}이/가 장바구니에 1개 추가되었습니다.`);
            }
            state.totalPrice = state.cartItems.reduce((total, current) => total + current.price * current.quantity, 0);
        },
        outProduct: (state, action) => {
            const item = state.cartItems.find(i => i.productId === action.payload.productId);
            if (!item) return;

            if (item.quantity > 1) {
                item.quantity -= 1;
                toast.info(`${item.name} 1개가 장바구니에서 제거되었습니다.`);
            } else {
                state.cartItems = state.cartItems.filter(i => i.productId !== action.payload.productId);
                toast.info(`${item.name} 가 장바구니에서 삭제되었습니다.`);
            }
            state.totalPrice = state.cartItems.reduce((total, current) => total + current.price * current.quantity, 0);
        },
        clearCart: (state) => {
            state.cartItems = [];
            state.totalPrice = 0;
            toast.info("장바구니가 비워졌습니다.");
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchProducts.fulfilled, (state, action) => {
                state.loading = false;
                state.products = action.payload.content || [];
                state.totalPages = action.payload.totalPages || 0;
            })
            .addCase(fetchProducts.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })
            .addCase(deleteProduct.fulfilled, (state, action) => {
                state.products = state.products.filter(p => p.productId !== action.payload);
                state.cartItems = state.cartItems.filter(c => c.productId !== action.payload);
                state.totalPrice = state.cartItems.reduce((total, current) => total + current.price * current.quantity, 0);
                toast.info(`장바구니에서도 삭제되었습니다.`);
            })
            .addCase(searchProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(searchProducts.fulfilled, (state, action) => {
                state.loading = false;
                state.products = action.payload.content || [];
                state.totalPages = action.payload.totalPages || 0;
            })
            .addCase(searchProducts.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    }
})

export const { putProduct, outProduct, setPage, clearCart } = ProductSlice.actions;
export default ProductSlice.reducer;