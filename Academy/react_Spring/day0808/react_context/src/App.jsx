
import './App.css'
import Home from './components/Home'
import './components/style.css'
import { Sub1 } from './components/Sub1'
import { ThemeProvider } from './components/ThemeContext'
import { Parent } from './components/User'

function App() {

  return (
    <>
      <ThemeProvider>
        <Home />
      <Parent />

      <div className='root'>
        <Sub1 />
      </div>
      </ThemeProvider>
    </>
  )
}

export default App
