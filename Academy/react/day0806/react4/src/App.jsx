import { useState } from 'react'
import './App.css'
import Timer from './components/Timer';
import HookCanvas from './components/HookCanvas';
import DrawingCanvas from './components/DrawingCanvas';
import PopupMenu from './components/PopupMenu';



function App() {
  const [show, setShow] = useState(true);

  return (
    <div>
      <h1>타이머</h1>
      <button onClick={() => { setShow((prev) => { return !prev }) }}>
        {show ? "타이머 제거" : "타이머 보기"}
      </button>
      <hr />
      {show && <Timer />}
      {/* and연산자는 앞의 것이 false면 뒤의 것을 수행안함 */}

      <HookCanvas />
      <hr />

      <DrawingCanvas />
      <hr />

      <PopupMenu />
      <hr />
    </div>

    

  )
}

export default App
