from fastapi import FastAPI, Depends, Request, Form, HTTPException, status, Response
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
import os

from database import Base, engine, SessionLocal
import models


# --- 앱/템플릿/정적파일 ---
app = FastAPI()
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
templates = Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))

# --- DB 테이블 생성 ---
Base.metadata.create_all(bind=engine)

# --- DB 세션 의존성 ---
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# 1. 책 목록
# @app.get("/books", response_class=HTMLResponse)
# def list_books(request: Request, db: Session = Depends(get_db)):
#     books = db.query(models.Book).all()

#     return templates.TemplateResponse("books/list.html", {"request": request, "books": books})

@app.get("/books",response_class=HTMLResponse)
def list_books(request:Request,db:Session=Depends(get_db)):
    books=db.query(models.Book).all()
    book_data=[]
    for book in books:
        reviews=book.reviews # 해당 책에 대한 리뷰들을 리스트로 꺼냄
        avg_rating=round(sum(r.rating for r in reviews)/len(reviews),1) if reviews else None
        book_data.append({"book":book,"avg_rating":avg_rating})

    return templates.TemplateResponse("books/list.html",{"request":request,"books":book_data})

# 2. 책 등록 폼
@app.get("/books/new", response_class=HTMLResponse)
def new_book_form(request: Request):
    return templates.TemplateResponse("books/new.html", {"request": request})

# 3. 책 생성
@app.post("/books/create")
def create_book(
    title: str = Form(...),
    author: str = Form(...),
    year: int = Form(...),
    db: Session = Depends(get_db)
):
    book = models.Book(title=title, author=author, year=year)
    db.add(book)
    db.commit()
    return RedirectResponse(url="/books", status_code=303)

# 4. 책 상세 보기
@app.get("/books/{book_id}", response_class=HTMLResponse)
def detail_book(book_id: int, request: Request, db: Session = Depends(get_db)):
    book = db.query(models.Book).filter(models.Book.id == book_id).first()
    if not book:
        raise HTTPException(status_code=404, detail="Book not found")

    reviews = db.query(models.Review).filter(models.Review.book_id == book_id).all()
    return templates.TemplateResponse("books/detail.html", {"request": request, "book": book, "reviews": reviews})

# 5. 책 수정 폼
@app.get("/books/edit/{book_id}", response_class=HTMLResponse)
def edit_book_form(book_id: int, request: Request, db: Session = Depends(get_db)):
    book = db.query(models.Book).filter(models.Book.id == book_id).first()
    if not book:
        raise HTTPException(status_code=404, detail="Book not found")
    return templates.TemplateResponse("books/edit.html", {"request": request, "book": book})

# 6. 책 업데이트
@app.post("/books/update/{book_id}")
def update_book(
    book_id: int,
    title: str = Form(...),
    author: str = Form(...),
    year: int = Form(...),
    db: Session = Depends(get_db)
):
    book = db.query(models.Book).filter(models.Book.id == book_id).first()
    if not book:
        raise HTTPException(status_code=404, detail="Book not found")

    book.title = title
    book.author = author
    book.year = year
    db.commit()
    return RedirectResponse(url="/books", status_code=303)

# 7. 책 삭제
@app.get("/books/delete/{book_id}")
def delete_book(book_id: int, db: Session = Depends(get_db)):
    book = db.query(models.Book).filter(models.Book.id == book_id).first()
    if not book:
        raise HTTPException(status_code=404, detail="Book not found")
    db.delete(book)
    db.commit()
    return RedirectResponse(url="/books", status_code=303)

# 리뷰 파트 추가
# 리뷰 추가 폼
@app.get("/books/{book_id}/reviews/new",response_class=HTMLResponse)
def new_review_form(book_id:int,request:Request,db:Session=Depends(get_db)):
    book = db.query(models.Book).filter(models.Book.id == book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail="Book not found")
    return templates.TemplateResponse("books/review_form.html",{"request":request,"book":book,"book_id":book_id})

# 리뷰 추가
@app.post("/books/{book_id}/reviews/create")
def create_review(
    book_id:int,
    content:str=Form(...),
    rating:int=Form(...),
    db:Session=Depends(get_db)
):
    book=db.query(models.Book).filter(models.Book.id==book_id).first()
    if not book:
        raise HTTPException(status_code=404,detail="Book not found")

    review=models.Review(content=content,rating=rating,book_id=book_id)
    db.add(review)
    db.commit()
    return RedirectResponse(url=f"/books/{book_id}",status_code=303)

# 리뷰 수정 폼
@app.get("/reviews/{review_id}/edit",response_class=HTMLResponse)
def edit_review_from(review_id:int,request:Request,db:Session=Depends(get_db)):
    review=db.query(models.Review).filter(models.Review.id==review_id).first()
    if not review:
        raise HTTPException(status_code=404,detail="Review no found")
    return templates.TemplateResponse("books/review_form.html",{"request":request,"review":review})

# 리뷰 수정
@app.post("/reviews/{review_id}/update")
def update_review(
    review_id:int,
    rating:int=Form(...),
    content:str=Form(...),
    db:Session=Depends(get_db)
    ):
    review=db.query(models.Review).filter(models.Review.id==review_id).first()
    if not review:
        raise HTTPException(status_code=404,detail="Review not found")
    review.rating=rating
    review.content=content
    db.commit()
    return RedirectResponse(f"/books/{review.book_id}",status_code=303)

# 리뷰 삭제
@app.get("/reviews/{review_id}/delete")
def delete_review(review_id:int,db:Session=Depends(get_db)):
    review=db.query(models.Review).filter(models.Review.id==review_id).first()
    if not review:
        raise HTTPException(status_code=404,detail="Review not found")
    book_id=review.book_id
    db.delete(review)
    db.commit()
    return RedirectResponse(f"/books/{book_id}",status_code=303)


# 검색, 로그인, 장르, 추천, 댓글(좋아요, 대댓글)
# 실행은 app.py파일이 위치한 곳에서 uvicorn app:app --reload 실행
