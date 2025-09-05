from fastapi import FastAPI, Depends, Request, Form, HTTPException, status, Response
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
import os

from database import Base, engine, SessionLocal
import models

app=FastAPI()
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
templates = Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))


Base.metadata.createall()

def get_db():
    db=SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/books")
def list_book(request:Request,db:Session=Depends(get_db)):
    books=db.query(models.Book).all()
    return templates.TemplateResponse("/book/list.html",{"request":request,"books":books})

@app.get("/books/new")
def new_book_form(request:Request):
    return templates.TemplateResponse("/books/new",{"request":request})