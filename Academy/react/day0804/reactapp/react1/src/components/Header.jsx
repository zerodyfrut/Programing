const Header =() =>{

    const number=10;

    return(
        <header>
            <h1>Header</h1>
            <h3>number</h3>
            <h3>{number}</h3>
            <h3>{number+10}</h3>
            <h3>{number%2==0 ?"짝수": "홀수"}</h3>
        </header>
    );
}

export default Header; //모듈화