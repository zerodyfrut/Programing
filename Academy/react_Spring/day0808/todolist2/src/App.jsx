import { useReducer, useEffect } from "react";
import TodoEditor from "./components/TodoEditor";
import TodoList from "./components/TodoList";

const STORAGE_KEY = "todo_data";
// 배열이나 객체는 안에 저장된걸 바꿀수 있으나,
// 문자열 등 고정된 건 바꾸지 못함.

const reducer = (state, action) => {
  // state(todos값을 가리킴)
  // action : dispatch를 통해 전달된 객체?
  switch (action.type) {
    case "INIT":
      return action.data;
    case "CREATE":
      return [...state, action.data];
    case "TOGGLE":
      return state.map((item) =>
        item.id === action.id ? { ...item, done: !item.done } : item
      );
    case "DELETE":
      return state.filter((item) => item.id !== action.id);
    default:
      return state;
  }
};

function App() {
  const [todos, dispatch] = useReducer(reducer, []);
  // 상태,todos 선언, dispatch 를 통해 reducer 함수로 전달, 처음 전달 값은 [].
  // 추후 reducer 함수의 return 을 통해 todos 상태가 변경됨

  useEffect(() => {
    // 처음 렌더링될때 수행(안에 return 없고, 함수 끝에 배열속 값이 없음.)
    const rawData = localStorage.getItem(STORAGE_KEY);
    // 뭔지 모르지만 localStorage의 아이템값을 가져와 STORAGE_KEY를 넣어서 rawData에 대입
    if (rawData) {
      dispatch({ type: "INIT", data: JSON.parse(rawData) });
      // rawData 값이 true, 즉 값이 있으면
      // dispatch 를 통해 reducer 수행
    }
  }, []);

  useEffect(() => {
    //이건 todos가 바뀔때 마다 렌더링 됨.
    localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
    // 뭔지 모르지만 localStorage의 아이템값에 뭔지 모르지만 JSON 형태의 todos를 넣음
  }, [todos]);

  const onCreate = (text, priority) => {
    // 두개의 매개변수를 받아 작업하는 함수
    dispatch({
      type: "CREATE",
      data: {
        id: Date.now(),
        text,
        priority,
        done: false,
        createdDate: new Date().toISOString(),
      },
      // dispatch를 통해 reducer 수행
    });
  };

  const onToggle = (id) => {
    dispatch({ type: "TOGGLE", id });
  };

  const onDelete = (id) => {
    dispatch({ type: "DELETE", id });
  };
  //각각 id 매개변수를 받아, dispatch로 reducer 수행

  return (
    <div className="App">
      <h1>todo 리스트</h1>
      <TodoEditor onCreate={onCreate} />
      {/* 뭔지모르지만  TodoEditor의 onCreate에 현파일의 OnCreate 값을 넣음? */}
      <TodoList todos={todos} onToggle={onToggle} onDelete={onDelete} />
      {/* TodoList에 각종 값을 넣어줌? */}
    </div>
  );
}

export default App;