const Button= ({text, color,children})=>{
    //이벤트 발생시 수행할 함수
    const onClickButton1=(e)=>{
        
        console.log(e);
        console.log(text);

    }

//함수호출
    return(
        <button onClick={onClickButton1} style={{color : color}}>
        {text}
        {children}
        </button>
    );

}

export default Button;