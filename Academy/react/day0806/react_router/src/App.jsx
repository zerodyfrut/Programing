import { useState } from 'react'

import './App.css'
import Main from './components/Main'
import Product from './components/Product'
import NotFound from './components/NotFound'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Header from './components/Header'
import TodoList from './components/TodoList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div className='App'>
        <BrowserRouter>
          <Header />
          {/* Header은 항상 유지 */}
          <Routes>
            <Route path="/" element={<Main />}></Route>
            {/* 기본 경로("/")=main */}
            
            <Route path="/product/:productId/:f" element={<Product/>}></Route>
            {/* product로 시작하고 :/= 값이 바뀌는 경우, 템블릿 변수와 유사 */}

            {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는 경우  처리 */}
            <Route path="*" element={<NotFound />}></Route>
            {/* 명시된 경로가 아니면(*)=NotFound */}

          </Routes>
        </BrowserRouter>

      </div>
      <TodoList/>
    </>
  )
}

export default App
