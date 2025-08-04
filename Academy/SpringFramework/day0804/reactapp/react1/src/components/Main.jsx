const Main= () => {
    const fruits=['apple','banana','peach']
    return(
        <main>
            <h1>Main</h1>

            <ul>
                {fruits.map((f,index)=>
                    <li key={index}>{f}</li>
                )}
            </ul>
        </main>

    );
}

export default Main; //외부에서 사용할 수 있도록