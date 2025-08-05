import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App'; //대문자 시작 태그 : 커스텀 태그
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(//랜더링 : 화면을 만듦
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
