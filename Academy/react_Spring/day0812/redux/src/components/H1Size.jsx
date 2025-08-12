import { useSelector } from "react-redux"

const H1Size=()=>{
    const sizeCss=useSelector((state)=>state.sizer);
    return(
        <>
        <h1 style={sizeCss}>This is Heading</h1>
        </>
    );
}

export default H1Size;