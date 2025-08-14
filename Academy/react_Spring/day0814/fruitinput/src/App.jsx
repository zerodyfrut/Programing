import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SearchFilter from './assets/components/SearchFilter'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <SearchFilter/>
    </>
  )
}

export default App
