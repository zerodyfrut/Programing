import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

const Hello = () => {
  return <div>Hello</div>
}

//React는 하나의 부모 요소로 래핑된 JSX 노드만 랜더링
createRoot(document.getElementById('root')).render(
  <>
  <App/>
  
    {/* <Header />
    <Main />
    <Hello/>
    <Footer />
    <Hello/> */}
  </>
)
// App.jsx의 function App(){}을 return 하고있는 App 컴포넌트를 실행