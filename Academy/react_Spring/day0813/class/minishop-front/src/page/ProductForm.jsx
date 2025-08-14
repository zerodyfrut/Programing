import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function ProductForm() {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [image, setImage] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append("name", name);
        formData.append("description", description);
        if (image) formData.append("image", image);

        try {
            //axios 작업추가
            await axios.post("http://localhost:8080/api/products", formData, {
                headers: { "Content-Type": "multipart/form-data" }
            })
            alert("상품이 등록되었습니다.");
            navigate("/");
        } catch (error) {
            console.error(error);
            alert("상품 등록 실패");
        }
    }

    return (
        <div>
            <h2>상품 등록</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>상품명</label>
                    <input value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div>
                    <label>설명</label>
                    <input value={description} onChange={(e) => setDescription(e.target.value)} required />
                </div>
                <div>
                    <label>이미지</label>
                    <input type="file" accept="image/*" onChange={(e) => setImage(e.target.files[0])} />
                </div>
                <button type="submit">등록</button>
            </form>
        </div>
    )
}

export default ProductForm;