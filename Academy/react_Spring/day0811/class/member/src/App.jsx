import { useState } from 'react'

import './App.css'
import MemberForm from './components/MemberForm'
import { Link, Route, Routes } from 'react-router-dom'
import MemberList from './components/MemberList'

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <nav>
        <Link to="/" style={{ marginRight: '10px' }}>회원 등록</Link>
        <Link to='/members'>회원 목록</Link>
      </nav>

      <hr />

      <Routes>
        <Route path='/' element={<MemberForm />} />
        <Route path='/members' element={<MemberList />} />

      </Routes>

    </div>
  )
}

export default App
