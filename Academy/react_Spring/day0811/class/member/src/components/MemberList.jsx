import axios from "axios";
import { useEffect, useState } from "react";

export default function MemberList() {
    const [members, setMembers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchMembers = async () => {
            try {
                const res = await axios.get(`http://localhost:8080/api/members`);
                setMembers(res.data);
            } catch (err) {
                setError("회원 목록을 불러오는 데 실패했습니다.")
            } finally {
                setLoading(false);
            }
        };
        fetchMembers();
    }, []);

    if (loading) return <p>로딩 중...</p>
    if (error) return <p style={{ color: 'red' }}>{error}</p>
    const onDelete = (idNo) => {
        //DB 삭제
        const deleteMember = async () => {
            try {
                setLoading(true);
                const res = await axios.delete(`http://localhost:8080/api/members/${idNo}`);
            } catch (err) {
                setError('회원 삭제 실패했습니다.');
            } finally {
                setLoading(false);
            }
        };
        deleteMember();
        setMembers(members.filter((item) => item.idNo !== idNo));
    };
    
    // const onDelete = (idNo) => {
    //     const deleteMember = async () => {
    //         try {
    //             setLoading(true);
    //             const res = await axios.delete(`http://localhost:8080/api/members/${idNo}`);
    //         } catch (err) {
    //             setError("회원 삭제 실패");
    //         } finally {
    //             setLoading(false);
    //         }
    //     };
    //     deleteMember();
    //     setMembers(members.filter((item) => item.idNo !== idNo));
    // }

    return (
        <div>
            <h2>회원 목록</h2>
            {members.length === 0 ? (
                <p>등록된 회원이 없습니다..</p>
            ) : (
                <ul>
                    {members.map((member) => (
                        <li key={member.idNo}>
                            {member.name} ({member.id} - {member.role})<br />
                            전화 : {member.phone}<br />
                            주소 : {member.address}<br />
                            {member.image && (
                                <img src={`http://localhost:8080${member.image}`}
                                    alt={member.name}
                                    style={{ width: 100, height: 100, objectFit: "cover", marginTop: 8 }} />
                            )}
                            <button onClick={() => onDelete(member.idNo)}>삭제</button>
                            <hr />
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}