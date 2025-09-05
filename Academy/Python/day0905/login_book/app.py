from fastapi import FastAPI,Depends,Request,Form,HTTPException,status,Response
from fastapi.responses import HTMLResponse,RedirectResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
import os

from database import Base, engine, SessionLocal
import models
from login_book.schemas import UserCreate, ItemIn
import login_book.auth as auth

#--- 앱/템플릿 정적파일 ---

app=FastAPI()
Base_DIR=os.path.dirname(os.path.abspath(__file__))
templates=Jinja2Templates