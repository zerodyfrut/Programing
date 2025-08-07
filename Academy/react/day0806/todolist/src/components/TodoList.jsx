import { useRef, useState } from "react";

const TodoList = () => {
    const [todos, setTodos] = useState([]);
    const [newTodo, setNewTodo] = useState("");
    const [index, setIndex] = useState(null);
    const focus = useRef();

    const handleInputChange = (e) => {
        setNewTodo(e.target.value);
    }

    const handAddTodo = () => {
        if (newTodo.trim() !== "") {
            if (index == null) {
                setTodos([...todos, newTodo]);
                setNewTodo("");
                focus.current.focus();
            } else {
                // 아직 작성 필요

            }
        }
    }


function handleDeleteTodo(index) {
    const newTodos = todos.filter((_, i) => i !== index);
    setTodos(newTodos);
    focus.current.focus();
}


const updateTodo = (index) => {
    const update = setTodos(todos[index]);
    focus.current.focus();
}


    return (
        <div className="todo-list">
            <h1>To-Do List</h1>
            <div className="todo-input">
                <input type="text" value={newTodo}
                    onChange={handleInputChange} placeholder="Add a new task"
                    ref={focus} />

                <button onClick={handAddTodo}>Add</button>
            </div>
            <ul className="todo-items">
                {todos.map((todo, index) => (
                    <li key={index}>{todo}
                    {/* <button onClick={() => updateTodo(index)}>Update</button> */}
                    <button onClick={() => handleDeleteTodo(index)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default TodoList;