import { Link, Outlet } from "react-router-dom"

const Layout=()=>{
    return(
        <>
        <nav>
          <Link to="/add-product">상품등록</Link>
          <Link to="/product">상품목록</Link>
          <Link to="/cart">장바구니</Link>
        </nav>
        <main>
            <Outlet/>
        </main>

        </>
    )
}
export default Layout;