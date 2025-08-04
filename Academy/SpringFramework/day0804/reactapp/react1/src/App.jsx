// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import Header from './components/Header.jsx'
import Main from './components/Main.jsx'
import Footer from './components/Footer.jsx'
import Button from './components/Button.jsx'
import Child from './components/Child.jsx'
import Nav from './components/Nav.jsx'

// function Header(){
// return(
//   <header>
//     <h1>Header 입니다.</h1>
//   </header>
// );
// }

function App() {
  const b1Props = {
    text: "메일", color: "green", a: 1, b: 2
  }

  const topics = [
    { id: 1, title: 'html', body: 'html is...' },
    { id: 2, title: 'css', body: 'css is...' },
    { id: 3, title: 'javascript', body: 'javascript is...' },
  ]


  // const [count, setCount] = useState(0)

  return (
    <>
      <Header />
      <Nav topics={topics}/>

      {/* 객체 안의 속성들을 컴포넌트의 props로 한꺼번에 전달 */}
      <Button {...b1Props} />
      <Button text={"클라우드"} color={"blue"} a={1} />
      <Button text={"apple"} >
        <Child />
        {'hello'}
      </Button>
      <Main />
      <Footer />
    </>
  )
}


{/* <div>
  <a href="https://vite.dev" target="_blank">
  <img src={viteLogo} className="logo" alt="Vite logo" />
  </a>  
  <a href="https://react.dev" target="_blank">
  <img src={reactLogo} className="logo react" alt="React logo" />
  </a>  
  </div>  
  <h1>Vite + React</h1>
  <div className="card">
  <button onClick={() => setCount((count) => count + 1)}>
  count is {count}
  </button>  
  <p>
  Edit <code>src/App.jsx</code> and save to test HMR
  </p>  
  </div>  
  <p className="read-the-docs">
  Click on the Vite and React logos to learn more
  </p> */}

export default App

// export{App,Header}

