from fastapi import FastAPI, Depends,HTTPException,Request,Form
from fastapi.responses import RedirectResponse,HTMLResponse
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
from database import SessionLocal, engine, Base
import models,schemas
import os


# 테이블 생성
Base.metadata.create_all(bind=engine)

app=FastAPI()

# 템플릿 지정
BASE_DIR=os.path.dirname(os.path.abspath(__file__))
templates=Jinja2Templates(directory=os.path.join(BASE_DIR,"templates"))

# DB 세션 의존성
def get_db():
    db=SessionLocal()
    try:
        yield db # return 대신 yield -> FastAPI가 의존성 관리
    finally:
        db.close() # 응답 반환시 세션 정리


# 메모 목록
@app.get('/',response_class=HTMLResponse)
def read_notes(request:Request, db:Session=Depends(get_db)):
    # Depends(get_db) : FastAPI가 세션을 주입
    notes=db.query(models.Note).all()
    return templates.TemplateResponse("memo/index.html",{"request":request,"notes":notes})


# 새 메모 작성 폼
@app.get('/notes/new',response_class=HTMLResponse)
def new_note_form(request:Request):
    return templates.TemplateResponse("/memo/form.html",{"request":request})


# 메모 상세보기
@app.get('/notes/{note_id}',response_class=HTMLResponse)
def read_note(request:Request,note_id:int, db:Session=Depends(get_db)):
    note= db.query(models.Note).filter(models.Note.id==note_id).first()
    if not note:
        raise HTTPException(status_code=404,detail="Note not found")
    return templates.TemplateResponse("memo/detail.html",{"request": request, "note":note})


# 메모 생성 처리
@app.post("/notes/",response_class=HTMLResponse)
def create_note(
    title:str=Form(...),
    content:str=Form(...),
    db:Session=Depends(get_db)
):
    db_note=models.Note(title=title,content=content)
    db.add(db_note)
    db.commit()
    return RedirectResponse("/",status_code=303)
        # 303 : post 요청해서 성공했지만, 다른 URL을 get방식으로 요청해서 결과를 확인한다.
        # 리다이렉스시 사용


# @app.post("/notes/", response_model=schemas.NoteOut)
# def create_note(note_in: schemas.NoteCreate, db: Session = Depends(get_db)):
#     # DB 객체 생성
#     db_note = models.Note(title=note_in.title, content=note_in.content)
#     db.add(db_note)
#     db.commit()
#     db.refresh(db_note)  # 방금 추가한 객체를 DB에서 다시 불러옴
#     return db_note  # NoteOut 스키마로 자동 변환되어 반환


# 메모 수정 폼
@app.get("/notes/{note_id}/edit",response_class=HTMLResponse)
def edit_note_form(request:Request,note_id:int,db:Session=Depends(get_db)):
    note=db.query(models.Note).filter(models.Note.id==note_id).first()
    if not note:
        raise HTTPException(status_code=404,detail="Note not found")
    return templates.TemplateResponse("memo/edit.html",{"request":request,"note":note})


# 메모 수정 처리
@app.post("/notes/{note_id}/edit")
def update_note(
    note_id:int,
    title:str=Form(...),
    content:str=Form(...),
    db:Session=Depends(get_db)
):
    note=db.query(models.Note).filter(models.Note.id==note_id).first()
    if not note:
        raise HTTPException(status_code=404,detail="Note not found")
    
    note.title=title
    note.content=content
    db.commit()
    return RedirectResponse(f"/notes/{note_id}",status_code=303)


# 메모 삭제 처리
@app.post("/notes/{note_id}/delete")
def delete_note(note_id:int, db:Session=Depends(get_db)):
    note=db.query(models.Note).filter(models.Note.id==note_id).first()
    if not note:
        raise HTTPException(status_code=404,detail="Note not found")
    
    db.delete(note)
    db.commit()
    return RedirectResponse('/',status_code=303)


# uvicorn app:app --reload
