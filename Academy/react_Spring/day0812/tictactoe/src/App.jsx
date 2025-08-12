import { useState } from 'react';
import './style.css';

function Square({ value, onSquareClick }) {
  // value와 onSquareClick 함수를 변수로 같는 Square 함수 선언
  return (
    <button className="square" onClick={onSquareClick}>
      {/* square라는 이름의 버튼 생성, 클릭시, onSquareClick 수행 */}
      {value}
      {/* value값(x,o) 출력 */}
    </button>
  );
}

function Board({ xIsNext, squares, onPlay }) {
  // 세개의 변수를 가진 Board 함수
  function handleClick(i) {
    // onSquareClick 수행시 수행되는 함수

    if (calculateWinner(squares) || squares[i]) {
      // calculateWinner함수값 또는 squares[i] 이 true (존재하면)
      return;
      // 종료
    }

    const nextSquares = squares.slice();
    // nextSquares 변수 선언, squares를 복사
    if (xIsNext) {
      // xIsNext 값이 t:X, f:O  
      nextSquares[i] = 'X';
    } else {
      nextSquares[i] = 'O';
    }
    onPlay(nextSquares);
    // nextSquares를 변수로한 onPlay 수행

  }

  const winner = calculateWinner(squares);
  // squares 를 변수로 같는 calculateWinner 함수 수행, return (x,o)을 winner에 대입
  let status;
  // status 변수 선언
  if (winner) {
    // winner이 true면 (값이 있으면)
    status = 'Winner: ' + winner;
    // 해당문자열 + winner를 status 에 대입
  } else {
    // winner이 false면 (값이 없으면)
    status = 'Next player: ' + (xIsNext ? 'X' : 'O');
    // 해당문자열 + x,o를 status 에 대입
  }

  return (
    <>
      <div className="status">{status}</div>
      {/* div 영역이름 status 및 status 출력 */}
      <div className="board-row">
        {/* div 영역이름 board-row */}
        <Square value={squares[0]} onSquareClick={() => handleClick(0)} />
        <Square value={squares[1]} onSquareClick={() => handleClick(1)} />
        <Square value={squares[2]} onSquareClick={() => handleClick(2)} />
        {/* Square 함수의 값(Game에서 전달한 최근스퀘어) onSquareClick 함수 전달  */}
      </div>
      <div className="board-row">
        {/* div 영역이름 board-row */}

        <Square value={squares[3]} onSquareClick={() => handleClick(3)} />
        <Square value={squares[4]} onSquareClick={() => handleClick(4)} />
        <Square value={squares[5]} onSquareClick={() => handleClick(5)} />
        {/* Square 함수의 값(Game에서 전달한 최근스퀘어) onSquareClick 함수 전달  */}

      </div>
      <div className="board-row">
        {/* div 영역이름 board-row */}

        <Square value={squares[6]} onSquareClick={() => handleClick(6)} />
        <Square value={squares[7]} onSquareClick={() => handleClick(7)} />
        <Square value={squares[8]} onSquareClick={() => handleClick(8)} />
        {/* Square 함수의 값(Game에서 전달한 최근스퀘어) onSquareClick 함수 전달  */}

      </div>
    </>
  );
}

export default function Game() {
  // 아마 시작점? export 처리되어있으니, 다른곳에서 불러올 수 있는 것은 이것이기 때문.
  const [history, setHistory] = useState(() => [Array(9).fill(null)]);
  // 9개짜리 배열을 만들고 모두 null값을 부여.
  // []안에 Array를 선언 -> 2차원 배열
  const [currentMove, setCurrentMove] = useState(0);
  // 최근 동작 저장 -> 인덱스로 하나?
  // 진행 차수를 저장
  const xIsNext = currentMove % 2 === 1;
  // 최근 동작이 2의 배수인지 확인? (t/f로 저장)
  // 플레이어가 2명
  const currentSquares = history[currentMove];
  // 최근스퀘어 = 배열[진행차수];
  // 9칸짜리 1차원 배열

  function handlePlay(nextSquares) {
    // nextSquares 라는 인수를 받는 함수 선언
    const nextHistory = [...history.slice(0, currentMove + 1), nextSquares];
    // nextHistory 는 hisory 배열을 0부터 최근 동작+1 까지(slice는 뒤에 선언된 곳 앞까지 잘라서 복사)
    // + nextSquares 최근 움직인 배열추가
    setHistory(nextHistory);
    // nextSquares 를 추가한 배열을 history 에 세팅
    setCurrentMove(nextHistory.length - 1);
    // 최근 동작은 nextHistory 의 길이 -1 (배열은 0부터 세기 때문)
  }

  function jumpTo(nextMove) {
    // nextMove라는 변수를 가진 jumpTo 함수 선언
    if (currentMove === nextMove) {
      // 최근 동작이 nextMove가 맞는지 확인
      return;
      // 맞으면 종료
    }

    setCurrentMove(nextMove);
    // 최근동작에 nextMove 값을 세팅
  }

  const moves = history.map((squares, move) => {
    // squares 배열에서 move 인덱스의 값을 순차적으로 꺼내서 작동함
    let description;
    // 변수 선언
    if (move > 0) {
      // move값이 0보다 크면
      description = 'Go to move #' + move;
      // 변수에 해당 문자열과 move값 넣음
    } else {
      description = 'Go to game start';
      // move가 0이하면 해당 문자열을 삽입
    }
    return (
      <li key={move}>
        {/* move를 키값으로 갖는 목록 생성 */}
        <button onClick={() => jumpTo(move)}>{description}</button>
        {/* 클릭하면 jumpTo(move)를 수행, 버튼 내용은 {description} */}
      </li>
    );
  });

  return (
    <div className="game">
      {/* div 영역 이름은 game */}
      <div className="game-board">
        {/* div 영역 이름은 game-bord */}
        <Board xIsNext={xIsNext} squares={currentSquares} onPlay={handlePlay} />
        {/* xISNext 와 squares, onPlay 값을 Board에 넘김 */}
        {/* 어떤의미로는 실직적인 시작점 */}
      </div>
      <div className="game-info">
        {/* div 영역 이름 game-info */}
        <ol>{moves}</ol>
        {/* move를 출력 (history의 내용을 li에 넣은 것) */}
      </div>
    </div>
  );
}

function calculateWinner(squares) {
  // squares(game의 최근 스퀘어)를 변수로 받는 calculateWinner 함수 선언
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],

    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],

    [0, 4, 8],
    [2, 4, 6],
    // 승리 조건에 해당하는 배열들
  ];

  for (let i = 0; i < lines.length; i++) {
    //  승리조건들에 해당하는 배열들을 순회함
    const [a, b, c] = lines[i];
    // a,b,c 라는 배열 선언, 승리조건에 대한 배열을 순차적으로 대입
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
    // (squares[a]) && (squares[a] === squares[b]) && (squares[a] === squares[c])
    // 이렇게 묶을수 있으려나.
    // squares에는 x,o (플레이어의 기호) 가 들어있고, 해당위치(승리하는 조건 a,b,c)가 
    // 모두 같은 기호(같은 플레이어면)

      return squares[a];
    // 해당플레이어의 기호(x,o)를 리턴
    }
  }
  return null;
}

