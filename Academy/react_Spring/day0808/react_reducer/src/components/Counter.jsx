import { useReducer } from "react";

const Counter = () => {
    const initialState = { count: 0 };

    function reducer(oldstate, action) {
        switch (action.type) {
            case 'plus': {
                return { count: oldstate.count + 1 };
            }
            case 'minus': {
                return { count: oldstate.count - 1 };
            }
            case 'reset': {
                return initialState;
            }
            default:{
                throw new Error('Unknown action')
            }
        }
    }

    const [state, dispatch] = useReducer(reducer, initialState);
    
    return (
        <>
        <div>
            <p>Count : {state.count}</p>
            <button onClick={()=> dispatch({type : 'plus'})}>+</button>
            <button onClick={()=> dispatch({type : 'minus'})}>-</button>
            <button onClick={()=> dispatch({type : 'reset'})}>Reset</button>
            
        </div>
        </>
    )
}
export default Counter