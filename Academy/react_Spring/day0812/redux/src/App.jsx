
import { useDispatch, useSelector } from 'react-redux'
import './App.css'
import { decrement, increment } from './CounterSlice';
import Btn from './components/Btn';
import H1Size from './components/H1Size';

function App() {
  //useSelector() : store에서 값을 읽기
  const count = useSelector((state) => state.counter.value);
  const size =useSelector((state)=>state.sizer.fontSize)
  //useDispatch() : action 내보내기
  const dispatch = useDispatch();

  return (
    <>
      <div>
        <h1>Count:{count}</h1>
        <button onClick={() => dispatch(increment())}>증가</button>
        <button onClick={() => dispatch(decrement())}>감소</button>
      </div>
      <hr />
      <p>Size : {size} </p>
      <H1Size />
      <Btn />
      

    </>
  )
}

export default App
