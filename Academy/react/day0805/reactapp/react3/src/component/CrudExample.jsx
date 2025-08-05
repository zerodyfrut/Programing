import { useRef } from "react";
import { useState } from "react";

const CrudExample = () => {
    const [people, setPeople] = useState([]);  // 목록 상태
    const [name, setName] = useState("");  // 입력필드 상태
    const [editIndex, setEditIndex] = useState(null); // 수정 모드 여부
    const inputRef = useRef(); //포커스용

    // 항목 추가 또는 수정
    const handleSubmit = () => {
        if (name.trim() === "") return; //공백이면 그냥 리턴

        if (editIndex === null) { //editIndex 초기값이 null 이므로 처음에는 여기로옴
            //추가
            setPeople([...people, name]);//people 배열에 name 추가, ...없으면 2중 배열이 된다고 함..
        } else {
            //수정
            const updated = [...people]; // 불변성 때문에 직접 수정하는게 아닌, 값을 복사한 새로운 배열을 만들고 적용
            updated[editIndex] = name; // 해당 index에 값을 변경된 이름으로 수정
            setPeople(updated); //변경된 배열을 people에 저장
            setEditIndex(null); // index번호를 null로 변경, 다시 handleSubmit 에 진입시 -> 추가로 적용되도록
        }

        setName("");//빈문자열
        inputRef.current.focus(); // 포커스 적용
    };

    //수정시작
    const handleEdit = (index) => {//실질적 수정은 안하고, 해당하는 위치와 값을 가져오는 역할
        setName(people[index]); //input 태그에 해당 index의 value를 띄워줌
        setEditIndex(index); //index 번호저장-> handleSubmit에서 수정으로 넘어가도록
        inputRef.current.focus(); // 포커스 적용
    }

    //항목 삭제
    const handleDelete = (index) => {
        const updated = people.filter((_, i) => i !== index); //i 번째 인덱스 값을 삭제한 배열 생성
        //filter 조건에 참인 값만 남기고 새로운 배열 생성 (i !== index : i 와 index가 일치하지 않으면 참)
        setPeople(updated);// 삭제한 배열을 people에 저장
        
        //수정 중이던 항목이 삭제되면 editIndex 초기화
        if (editIndex === index) { // editIndex가 null이 아님-> 수정중, 해당 handleDelete에 인덱스 값이 전달됨,
                                   // 즉, 수정중, 삭제가 진행되면
            setEditIndex(null); // editIndex 값을 null,
            setName(""); //editIndex값을 ""으로 세팅
        }
    }

    return (
        <div style={{ padding: "20px" }}>
            <h2>CRUD</h2>

            <input ref={inputRef} value={name} placeholder="이름 입력"
                onChange={(e) => setName(e.target.value)} />

            <button onClick={handleSubmit}>
                {editIndex === null ? "추가" : "수정완료"}
            </button>
            <ul>
                {people.map((person, index) => (//people 배열의 index위치의 값을 person으로 두고 반복
                    <li key={index}>
                        {person}{" "}
                        <button onClick={() => handleEdit(index)}>수정</button>
                        {" "}
                        <button onClick={() => handleDelete(index)}>삭제</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CrudExample;