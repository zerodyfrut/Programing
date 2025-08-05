const FormExample= ()=>{

    const onSubmitForm=(e)=>{
        e.prevnetDefault(); // 액션 지정 페이지 이동 막기
        alert("Form Submit");
        //비동기통신으로 서버에 데이터 전송
    }

    return(
        <form onSubmit={onSubmitForm}>
            <input type="text" name="text1"/>
            <button type="submit">제출</button>
        </form>
    );
}

export default FormExample;