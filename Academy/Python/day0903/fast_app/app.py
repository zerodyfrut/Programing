from fastapi import FastAPI, Depends,HTTPException,Request,Form
from fastapi.responses import RedirectResponse,HTMLResponse
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
from database import SessionLocal, engin,Base
import models
import os


# 테이블 생성
Base.metadata.create_all(bind=engin)

app=FastAPI()

# 템플릿 지정
BASE_DIR=os.path.dirname(os.path.abspath(__file__))
templates=Jinja2Templates(directory=os.path.join(BASE_DIR,"templates"))

