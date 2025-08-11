import TodoItem from "./TodoItem";

const TodoList = ({ todos, onToggle, onDelete }) => {
  // 우선순위: 숫자가 낮을수록 높음
  const sortedTodos = [...todos].sort((a, b) => a.priority - b.priority);
  //기존배열에 sort하면 원본 배열이 바뀜  a-b : 사전순, b-a : 역순

  return (
    <div>
      {sortedTodos.map((item) => (
        <TodoItem
          key={item.id}
          {...item}
          onToggle={onToggle}
          onDelete={onDelete}
        />
      ))}
    </div>
  );
};

export default TodoList;