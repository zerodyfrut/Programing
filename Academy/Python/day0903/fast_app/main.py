#경로 및 요청 파라미터
import os
from fastapi import FastAPI, Form,Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from pydantic import BaseModel # fastapi에서 요청, 응답 데이터 생성 시 사용
                               # 데이터 검증, 변환을 자동으로 해주는 기본 클래스

# 파라미터로 넘어온 데이터를 저장할 객체
class Item(BaseModel):
    name : str # 입력값이 정의한 타입과 일치하지 않으면 예외 발생(변환가능)
    price : float
    is_offer:bool|None = None

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
app = FastAPI()
templates=Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))

@app.get("/")
def read_root():
    return {"message": "Hello, FastAPI!"}

# GET: /items/정수?q=문자열
@app.get("/items/{item_id}") # {item_id} : 경로 파라미터
def read_item(item_id: int, q: str|None = None): # q : 쿼리 파라미터
    return {"item_id": item_id, "q": q}

@app.get('/items',response_class=HTMLResponse)
def form_page(request: Request):
    return templates.TemplateResponse("form.html",{"request":request})

@app.post('/items.json')
def create_item(item: Item):
    print(item)
    return {'item_name': item.name, 'item_price':item.price}

@app.post("/items")
def create_item(
    name:str=Form(...),             # form에서 name 필드. ... 은 필수(required)를 의미
                                    # 기본값 넣기도 가능
    price:float =Form(...),         # form에서 price 필드
    is_offer:str | None=Form(None)  # form에서 is_offer 선택값, 기본 None
    ):
    #is_offer 문자열 처리("true"->True, ""/None -> False)
    is_offer_bool=is_offer=="true"
    return {"name":name,"price":price, "is_offer":is_offer_bool}

