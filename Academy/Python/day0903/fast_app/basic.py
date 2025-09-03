# fast api : 웹 어플리케이션 개발할 수 있는 파이썬 웹 프레임 워크
# 속도가 빠르고 자동 문서화 지원, 비동기(async/await)지원
# WAS가 따로 필요함 -> uvicorn에 얹어서 실행
# FastAPI는 기본적으로 JSON을 반환

# uvicorn 파일명:app --reload  (현재파일명 : basic)
# http://127.0.0.1:8000
# http://127.0.0.1:8000/docs(자동 문서)
# 원하는 ip및 옵션으로 적용도 가능함. uvicorn basic:app --host=0.0.0.0 --port=5678 --reload

from fastapi import FastAPI

app=FastAPI()

@app.get('/')
def read_root():
    return{"Hello":"World"}