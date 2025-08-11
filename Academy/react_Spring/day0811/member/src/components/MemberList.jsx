import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function MemberList() {
    const [members, setMembers] = useState([]);

    useEffect(() => {
        // 컴포넌트가 처음 렌더링 될 때 서버에서 데이터 받아오기
        axios.get("http://localhost:8080/api/members")
            .then((response) => {
                setMembers(response.data);  // 받아온 데이터를 상태에 저장
            })
            .catch((error) => {
                console.error("데이터 가져오기 실패:", error);
            });
    }, []); // 빈 배열: 최초 1회만 실행

    return (
        <div>
            <h2>멤버 목록</h2>
            {members.length === 0 && <p>데이터가 없습니다.</p>}
            <ul>
                {members.map(member => (
                    <li key={member.idNo} style={{ marginBottom: "20px" }}>
                        <p>ID: {member.id}</p>
                        <p>이름: {member.name}</p>
                        <p>전화번호: {member.phone}</p>
                        <p>주소: {member.address}</p>
                        <p>역할: {member.role}</p>
                        {member.image && (
                            <img
                                src={`http://localhost:8080${member.image}`}
                                alt={member.name}
                                style={{ width: "100px", height: "100px", objectFit: "cover" }}
                            />
                        )}
                        <hr />
                    </li>
                ))}
            </ul>
            <Link to="/">홈으로</Link>

        </div>
    );
}
