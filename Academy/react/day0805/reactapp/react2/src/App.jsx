import './App.css'
import { useState } from 'react'
import Button from './components/Button'
import Button2 from './components/Button2'
import FormExample from './components/FormExample'
import Count from './components/Count'
import Light from './components/Light'
import Parent from './components/Parent'
import ChangeColor from './components/ChangeColor'

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
