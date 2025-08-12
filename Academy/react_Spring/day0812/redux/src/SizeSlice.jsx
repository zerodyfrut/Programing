import { createSlice } from "@reduxjs/toolkit";

const initialState = { fontSize: 30 }

const SizeSlice = createSlice({
    name: "sizer",
    initialState,
    reducers: {

        bigger: (state) => {
            if (state.fontSize < 100)
                state.fontSize += 10;
        },

        smaller: (state) => {
            if (state.fontSize > 0)
                state.fontSize -= 10;
        }
    }
})

export const { bigger, smaller } = SizeSlice.actions;
export default SizeSlice.reducer;
