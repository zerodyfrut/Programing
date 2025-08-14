
import { Link, Outlet } from 'react-router-dom'
import './App.css'

function App() {

  return (
   <div>
    <header>
      <nav>
        <Link to="/">상품 리스트</Link>
        <Link to="/add-product">상품 등록</Link>
        <Link to="/cart">장바구니</Link>
      </nav>
    </header>
    <Outlet/>
   </div>
  );
}

export default App
