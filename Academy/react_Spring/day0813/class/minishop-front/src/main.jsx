import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { Provider } from 'react-redux'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ProductDetail from './page/ProductDetail.jsx'
import ProductForm from './page/ProductForm.jsx'
import Cart from './page/Cart.jsx'
import ProductList from './page/ProductList.jsx'
import store from './store.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />}>
            <Route path="/" element={<ProductList />} />
            <Route path="products/:id" element={<ProductDetail />} />
            <Route path="add-product" element={<ProductForm />} />
            <Route path="cart" element={<Cart />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </StrictMode>,



)
