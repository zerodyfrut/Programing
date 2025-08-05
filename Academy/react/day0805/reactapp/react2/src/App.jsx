import './App.css'
import { useState } from 'react'
import Button from './component/Button'
import Button2 from './component/Button2'
import FormExample from './component/FormExample'
import Count from './component/Count'
import Light from './component/Light'
import Parent from './component/Parent'
import ChangeColor from './component/ChangeColor'

function App() {

  // const state= useState(1);
  // console.log(state)

  // const [state, setState] = useState(0);

  
  // return (
  //   <>
  //   <h1>{state}</h1>
  //     <button onClick={() => {
  //       setState(state + 1);
  //     }}> + </button>
  //   </>
  // )
  return (
    <>
      <Button text={"클라우드"} color={"blue"} a={1}/>
      <Button text={"apple"} />
      <Button2/>
      <FormExample/>
      <Count/>
      {/* <Light/> */}
      <Parent/>
      <ChangeColor/>
    </>
  )
}

export default App
