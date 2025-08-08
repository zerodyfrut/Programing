import dayjs from "dayjs";
import SockJS from 'sockjs-client';
import { CompatClient, Stomp } from '@stomp/stompjs';

import React, { useState, useRef, useEffect } from "react";

const ChatApp = () => {
    // 변수 선언
    const [messages, setMessages] = useState([]);
    const [username, setUsername] = useState('');
    const [connected, setConnected] = useState(false);
    const [input, setInput] = useState('');
    const stompClient = useRef(null);
    const focus1 = useRef("");
    const focus2 = useRef("");

    // 종료시 적용
    useEffect(() => {
        return () => disconnect();
    }, [])

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/ws-chat');
        stompClient.current = Stomp.over(socket);

        stompClient.current.connect({}, () => {  //{}안에는 헤더
            setConnected(true);

                stompClient.current.subscribe('/topic/public', (message) => {//scribe 등등
                // 해당 주소 및 매개변수(메세지)
                const data = JSON.parse(message.body);
                // 메세지 내용을 JSON화? 해서 data에 삽입
                setMessages((prev) => [...prev, data]);
                // Message에 data값 추가
            });

            stompClient.current.send('/app/chat.newUser', {}, JSON.stringify({//
                sender: username || 'Anonymous',
                type: 'JOIN'
            }));
        });
    }

    const disconnect = () => {
        if (stompClient.current) {
            stompClient.current.disconnect();

            setConnected(false);
        }
    }

    const sendMessage = () => {
        if (stompClient.current && input.trim()) {
            stompClient.current.send('/app/chat.send', {}, JSON.stringify({
                sender: username || 'Anoymous',
                content: input,
                type: 'CHAT'
            }));
            setInput("");
            focus1.current.focus();

        }
    }

    useEffect(() => {
        if (connected) {
            focus1.current?.focus(); // 메시지 입력창 포커스
        } else {
            focus2.current?.focus(); // 사용자명 입력창 포커스
        }
    }, [connected]);

    return (
        <div style={{ padding: 20 }}>
            <h2>💬 실시간 채팅</h2>

            {!connected && (
                <div>
                    <input type="text"
                        ref={focus2}
                        placeholder="사용자명"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        // 여기서 username에 값을 넣고,
                    />
                    <button onClick={connect}>접속</button>
                    {/* connect수행 */}
                </div>
            )}

            {connected && (
                <>
                    <div style={{ maxHeight: 300, overflowY: 'scroll', border: '1px solid gray', marginBottom: 10 }}>
                        {messages.map((msg, idx) => (
                            <div key={idx}>
                                <span style={{ color: msg.type === 'JOIN' ? 'green' : 'black' }}>
                                    {msg.sender}:</span>{msg.content || ''}
                                <small style={{ marginLeft: 10, color: '#888' }}>
                                    {msg.timestamp ? dayjs(msg.timestamp).format('HH:mm:ss') : ''}
                                </small>
                            </div>
                        ))}
                    </div>

                    <input type="text" placeholder="메세지 입력"
                        ref={focus1}
                        value={input}
                        onChange={(e) => setInput(e.target.value)}
                        onKeyDown={(e) => e.key === 'Enter' && sendMessage()} />

                    <button onClick={sendMessage}>전송</button>
                </>
            )}
        </div>
    )
}

export default ChatApp;