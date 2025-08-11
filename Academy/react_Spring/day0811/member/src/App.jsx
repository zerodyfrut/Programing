
import './App.css'
import MemberForm from './components/MemberForm'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import MemberList from './components/MemberList'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MemberForm />}></Route>
          <Route path="/members" element={<MemberList />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
