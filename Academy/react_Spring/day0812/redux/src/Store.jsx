import { configureStore } from "@reduxjs/toolkit";
import counterReducer from './CounterSlice';
import sizeReducer from './SizeSlice';

const store= configureStore({
    reducer:{
        counter: counterReducer,
        sizer:sizeReducer
    }
})

export default store;