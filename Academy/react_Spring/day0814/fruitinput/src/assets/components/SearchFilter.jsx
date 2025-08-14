import { useState } from "react";

const fruits = ['Apple', 'Banana', 'Cherrry', 'Fig', 'Grape', 'Lemon', 'Orange'];

export default function SearchFilter() {
    //사용자의 입력값을 저장하기 위한 state

    const [searchTerm, setSearchTerm] = useState("");
    // 검색어 (searchTerm)가 바뀔때마다 이 컴포넌트는 리렌더링 
    // -> Effect?
    // 렌더링 될때마다 필터링 로직이 새로 실행됩니다.

    const filteredFruits = fruits.filter(fruit => fruit.toLowerCase().includes(searchTerm.toLowerCase()));

    // 입력값으로 필터링(대소문자 구별x, ap검색시, Apple, Grape 둘 다 출력)
    // ->fruits를 lower? 등으로 소문자로 바꾸고, map으로 순회? 검색어와 같으면 값을 넣는다?
    // ->아님 다른 방법이?

    // const filteredFruits = fruits.filter(fruit =>
    //     fruit.toLowerCase().includes(searchTerm.toLowerCase())
    // );

    return (
        <div>
            <h2>과일 검색 필터</h2>
            <input type="text" placeholder="검색어 입력" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
            {/* input의 값이 변할떄마다 searchTerm state를 업데이트. */}
            {/* ->onChange 이벤트? */}
            {/* state 값 출력 */}
            <hr />

            {filteredFruits.length > 0 ? (
                // 필터링 된 결과가 1개 이상일 경우 목록을 렌더링
                <ul>
                    {/* 결과물 출력 */}
                    {filteredFruits.map((fruit,index)=>(
                        <li key={index} >{fruit}</li>
                    ))}
                </ul>
            ) : (
                // 필터링된 결과가 없을 경우, 메세지를 렌더링
                <p>검색결과가 없습니다.</p>
            )}

        </div>
    )

}