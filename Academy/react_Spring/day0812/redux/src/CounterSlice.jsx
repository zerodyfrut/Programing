import { createSlice } from "@reduxjs/toolkit";

const counterSlice=createSlice({
    name:'counter', //이름
    initialState:{value:0}, //초기값
    reducers:{ //reducer 함수들
        increment:(state)=>{state.value+=1;},
        decrement:(state)=>{state.value-=1;}
    }
})

export const{increment,decrement}=counterSlice.actions;
export default counterSlice.reducer;