import os
from fastapi import Form,FastAPI,Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

app = FastAPI()
templates = Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))

@app.get('/member',response_class=HTMLResponse)
def input_member(request:Request):
    return templates.TemplateResponse("input.html",{"request":request})

@app.post('/member')
def submit_member(
    name:str=Form(...),
    age:int=Form(...),
    mfm:str=Form(...),
    hobby:str|None=Form(None),
):
    return {"name":name, "age":age, "mfm":mfm, "hobby":hobby}


# uvicorn member:app --reload





# GPT : 결과페이지 추가.

# import os
# from fastapi import FastAPI, Form, Request
# from fastapi.responses import HTMLResponse
# from fastapi.templating import Jinja2Templates

# BASE_DIR = os.path.dirname(os.path.abspath(__file__))

# app = FastAPI()
# templates = Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))

# # GET 요청 → 폼 렌더링
# @app.get("/member", response_class=HTMLResponse)
# def input_member(request: Request):
#     """
#     회원 입력 폼 렌더링
#     """
#     return templates.TemplateResponse("input.html", {"request": request})

# # POST 요청 → 폼 데이터 처리
# @app.post("/member", response_class=HTMLResponse)
# def submit_member(
#     request: Request,
#     name: str = Form(...),
#     age: int = Form(...),
#     mfm: str = Form("male"),       # 기본값 남
#     habit: str | None = Form(None)
# ):
#     """
#     회원 폼 데이터를 받아 JSON 형태로 반환
#     """
#     result = {"name": name, "age": age, "gender": mfm, "habit": habit}
#     return templates.TemplateResponse("result.html", {"request": request, "result": result})
