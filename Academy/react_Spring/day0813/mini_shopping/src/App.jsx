
import './App.css'
import {  Route,  Routes } from 'react-router-dom'
import ProductList from './components/ProductList'
import ProductDetail from './components/ProductDetail'
import ProductForm from './components/ProductForm'
import Cart from './components/Cart'
import Layout from './components/Layout'

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>

          <Route path="/add-product" element={<ProductForm />} />
          <Route path="/product" element={<ProductList />} />
          <Route path="/product/:productId" element={<ProductDetail />} />
          <Route path="/cart" element={<Cart />} />
        </Route>
      </Routes>
    </>
  )
}

export default App
