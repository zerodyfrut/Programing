const Button2 = () => {
    //이벤트 발생시 수행할 함수

    const onClickButton2 = (name) => {
        alert(`${name}님이 클릭했어요`)
    }

    const onChangeInput = (e) => {
        console.log('입력값 : ' + e.target.value)
    }


    return (
        // <button onClick={onClickButton2('김솔데')}> 
        // 이렇게 하면 이벤트 발생하지 않아도 즉시 실행됨
        <>
            <button onClick={() => onClickButton2('김솔데')}>
                클릭
            </button>
            <br/>
            <input onChange={onChangeInput}/>
        </>
    );

}

export default Button2;