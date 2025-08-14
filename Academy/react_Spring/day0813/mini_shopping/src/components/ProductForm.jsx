import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ProductForm = () => {
    const [form, setForm] = useState({ name: "", price: "", description: "" });
    const [image, setImage] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!form.name || !form.price || !form.description) {
            alert("상품명, 가격, 상세설명은 필수입니다.");
            return;
        }

        try {
            const formData = new FormData();
            formData.append("name", form.name);
            formData.append("price", Number(form.price));
            formData.append("description", form.description);
            if (image) formData.append("image", image);

            // 서버 API 호출
            const res = await axios.post("http://localhost:8080/api/shopping", formData, {
                headers: { "Content-Type": "multipart/form-data" },
            });

            alert("상품등록 완료!");
            navigate("/product");
        } catch (err) {
            console.error(err);
            alert("상품등록 중 오류가 발생했습니다.");
        }

        setForm({ name: "", price: "", description: "" });
        setImage(null);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                placeholder="상품명"
                value={form.name}
                onChange={(e) => setForm({ ...form, name: e.target.value })}
            /><br />
            <input
                placeholder="가격"
                type="number"
                value={form.price}
                onChange={(e) => setForm({ ...form, price: e.target.value })}
            /><br />
            <textarea
                placeholder="상세설명"
                value={form.description}
                onChange={(e) => setForm({ ...form, description: e.target.value })}
            /><br />
            <input
                type="file"
                onChange={(e) => setImage(e.target.files[0])}
                accept="image/*"
            /><br />
            <button type="submit">상품등록</button>
        </form>
    );
};

export default ProductForm;
