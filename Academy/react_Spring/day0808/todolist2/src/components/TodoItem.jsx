const TodoItem = ({ id, text, done, createdDate, priority, onToggle, onDelete }) => {
  return (
    <div style={{ marginBottom: "8px" }}>
      <span
        onClick={() => onToggle(id)}
        style={{
          textDecoration: done ? "line-through" : "none",
          cursor: "pointer",
          marginRight: "8px"
        }}
      >
        {text}
      </span>
      <span>({["높음", "중간", "낮음"][priority - 1]})</span>
      <span style={{ marginLeft: "10px", color: "gray" }}>
        {new Date(createdDate).toLocaleString()}
      </span>
      <button onClick={() => onDelete(id)} style={{ marginLeft: "10px" }}>
        삭제
      </button>
    </div>
  );
};

export default TodoItem;