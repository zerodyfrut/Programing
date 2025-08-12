import { useState } from 'react';
import './style.css';

export default function Game() {
  const [history, setHistory] = useState(() => [Array(9).fill(null)]);
  const [currentMove, setCurrentMove] = useState(0); // 턴 저장
  const xIsNext = currentMove % 2 == 1; //홀수 :X , 짝수 ,:O
  const squares = history[currentMove]; //현재턴의 게임판 상태

  const onPlay = (nextSquares) => {
    const nextHistory = [...history.slice(0, currentMove + 1), nextSquares];
    setHistory(nextHistory);
    setCurrentMove(nextHistory.length - 1);
  }

  const jumpTo = (nextMove) => {
    if (currentMove === nextMove)
      return;
    setCurrentMove(nextMove);
  }

  const moves = history.map((squares, move) => {
    let description;
    if (move > 0)
      description = 'Go to move #' + move;
    else
      description = 'Go to game start';

    return (
      <li key={move}>
        <button onClick={() => jumpTo(move)}>{description}</button>
      </li>
    );
  })

  return (
    <div className='game'>
      <div className='game-board'>
        <Board xIsNext={xIsNext} squares={squares} onPlay={onPlay}></Board>
      </div>
      <div className='game-info'>
        <ol>{moves}</ol>
      </div>
    </div>
  )
}

const Board = ({ xIsNext, squares, onPlay }) => {

  const squareClick = (i) => {
    if (calculateWinner(squares) || squares[i])
      return;

    const nextSquares = squares.slice();

    if (xIsNext) {
      nextSquares[i] = 'X';
    } else {
      nextSquares[i] = 'O';
    }
    onPlay(nextSquares);
  }

  const winner = calculateWinner(squares);
  let status;
  if (winner)
    status = 'Winner : ' + winner;
  else
    status = 'Next Player : ' + (xIsNext ? 'X' : 'O');

  return (
    <>
      <div className='status'>{status}</div>
      <div className="board-row">
        <Square value={squares[0]} onSquareClick={() => squareClick(0)} />
        <Square value={squares[1]} onSquareClick={() => squareClick(1)} />
        <Square value={squares[2]} onSquareClick={() => squareClick(2)} />
      </div>
      <div className="board-row">
        <Square value={squares[3]} onSquareClick={() => squareClick(3)} />
        <Square value={squares[4]} onSquareClick={() => squareClick(4)} />
        <Square value={squares[5]} onSquareClick={() => squareClick(5)} />
      </div>
      <div className="board-row">
        <Square value={squares[6]} onSquareClick={() => squareClick(6)} />
        <Square value={squares[7]} onSquareClick={() => squareClick(7)} />
        <Square value={squares[8]} onSquareClick={() => squareClick(8)} />
      </div>
    </>
  )
}

const Square = ({ value, onSquareClick }) => {
  return (
    <button className='square' onClick={onSquareClick}>
      {value}
    </button>
  )
}

const calculateWinner = (squares) => {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],

    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],

    [0, 4, 8],
    [2, 4, 6]];
    // 승리 조건
  for (let i = 0; i < lines.length; i++) {
    const [a, b, c] = lines[i];
    if ((squares[a]) && (squares[a] === squares[b]) && (squares[a] === squares[c]))
      return squares[a];
  }
  return null;
}
