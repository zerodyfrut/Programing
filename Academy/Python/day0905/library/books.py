import os
from fastapi import Depends, FastAPI, Form, HTTPException, Request
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
import models
from database import Base,engine,SessionLocal

# table create
Base.metadata.create_all(bind=engine)
app=FastAPI()


# 템플릿 지정
BASE_DIR=os.path.dirname(os.path.abspath(__file__))
templates=Jinja2Templates(directory=os.path.join(BASE_DIR,"templates"))


# DB 세션 의존성
def get_db():
    db=SessionLocal()
    try:
        yield db
    finally:
        db.close()
    

# 책 목록 보기
@app.get('/',response_class=HTMLResponse)
def all_books(request:Request,db:Session=Depends(get_db)):
    books=db.query(models.Book).all()
    return templates.TemplateResponse('index.html',{'request':request,"books":books})


# 새로운 도서 등록 폼
@app.get('/books/new', response_class=HTMLResponse)
def new_book_form(request:Request):
    return templates.TemplateResponse('insert.html',{"request":request})


# 새로운 도서 등록
@app.post('/books/insert')
def new_book(
    title:str=Form(...),
    author:str=Form(...),
    year:int=Form(...),
    db:Session=Depends(get_db)
):
    db_book=models.Book(title=title,author=author,year=year)
    db.add(db_book)
    db.commit()
    return RedirectResponse('/',status_code=303)


# 도서 상세보기
@app.get("/books/{book_id}",response_class=HTMLResponse)
def detail_book(request:Request,book_id:int,db:Session=Depends(get_db)):
    book=db.query(models.Book).filter(models.Book.id==book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail='Book not found')
    return templates.TemplateResponse("detail.html",{'request':request,'book':book})


# 도서 수정폼
@app.get("/books/{book_id}/edit",response_class=HTMLResponse)
def edit_book_form(request:Request,book_id:int,db:Session=Depends(get_db)):
    book=db.query(models.Book).filter(models.Book.id==book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail='Book not found')
    return templates.TemplateResponse('edit.html',{'request':request,'book':book})


# 도서 수정
@app.post('/books/{book_id}/edit')
def edit_book(
    book_id:int,
    title:str=Form(...),
    author:str=Form(...),
    year:int=Form(...),
    db:Session=Depends(get_db)
):
    book=db.query(models.Book).filter(models.Book.id==book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail='Book not found')
    
    book.title=title
    book.author=author
    book.year=year

    db.commit()
    return RedirectResponse(f'/books/{book_id}',status_code=303)


# 도서 삭제
@app.post("/books/{book_id}/delete")
def delete_book(request:Request,book_id:int,db:Session=Depends(get_db)):
    book=db.query(models.Book).filter(models.Book.id==book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail='Book not found')
    db.delete(book)
    db.commit()
    return RedirectResponse('/',status_code=303)

# uvicorn books:app --reload