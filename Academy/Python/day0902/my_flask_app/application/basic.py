from flask import Flask

# flask : python WAS 라이브러리

app=Flask(__name__) # 현재 파일을 기준으로 내부 경로를 잡을 수 있음

# 요청 경로
# 해당 경로로 요청이 들어왔을 때, 수행할 함수

@app.route("/") # route = get
def test():
    print("hello world")
    return "Hello World"

if __name__=='__main__':
    app.run(
        "0.0.0.0", # 접속 허용 주소
        1234, # 포트번호 지정 안하면 5000번 포트로 연결
        debug=True # 정보출력 & 재시작
    )