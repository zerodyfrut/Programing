from typing import Optional
from uuid import uuid4 # 128bit 길이로 범용 고유 식별자 생성 (랜덤 값)
from fastapi import FastAPI, Form, UploadFile, Request, Query

from fastapi.responses import FileResponse, HTMLResponse
from fastapi.templating import Jinja2Templates
import os 

from fastapi.staticfiles import StaticFiles

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

app = FastAPI()
templates = Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))

app.mount("/static", StaticFiles(directory="static"), name="static")

@app.get("/member", response_class=HTMLResponse)
def fromGet(request: Request):
    return templates.TemplateResponse('member_input.html',{"request": request})

@app.post("/member")
def create_member(
    id: str = Form(...),
    pw: str = Form(...),
    comment: str = Form(...),
    gender: str = Form(...),
    hobby:Optional[list[str]]=Form(None) # hobby라는 이름으로 전달된 값이 없거나(None)
                                         # 혹은 list[str]타입이다
):
    # textarea에서 엔터 -> \r\n
    # html 에서 줄바꿈을 계속 표현하고 싶다면 <br>로 변경
    comment=comment.replace('\r\n','<br>')
    return {"ID": id, "gender": gender, "hobby": hobby, 'comment':comment}

@app.get('/file.up',response_class=HTMLResponse)
def fileGet(request:Request):
    return templates.TemplateResponse('file_input.html',{"request":request})

# image폴더에 파일을 넣음, 파일이름이 중복되지 않도록
@app.post('/file.up')
async def fileUp(
    request:Request,
    photo : UploadFile,
    title : str=Form()
    ):
    folder= os.path.join(BASE_DIR,'image')
    content= await photo.read() # 파일의 내용을 다 불러왔으면
    file_name= photo.filename # 오리지널 파일명
    type=file_name[-4:] # 확장자를 3글자로 가정. '.확장자' 
    file_name= file_name.replace(type,'') # 확장자명을 ''로 교체
    file_name=file_name + str(uuid4()) + type

    f= open(os.path.join(folder, file_name), 'wb') # b : binary(파일을 쓸 때)
    f.write(content)
    f.close()
    return templates.TemplateResponse("upload_result.html", {"request": request, "title": title, "file_name": file_name})



# 파일 업로드 한 내용을 화면에 출력(html)
    # html = "<html><head><meta charset=\"utf-8\"></head><body>"
    # html += "<h1>제목 : %s</h1>" % title
    # html += "<img src=\"file.get?fname=%s\">" % file_name
    # html += "<hr>"
    # html += "<a href=\"file.get?fname=%s\">%s</a>" % (file_name, title)
    # html += "</body></html>"

    # return HTMLResponse(html)

    
@app.get('/file.get')
async def getfile(fname):
     folder = os.path.join(BASE_DIR, 'image')
     return FileResponse(folder + fname, filename= fname)

# uvicorn member_class:app --reload