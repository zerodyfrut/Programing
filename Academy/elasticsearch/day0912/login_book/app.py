from fastapi import FastAPI,Depends,Request,Form,HTTPException,status,Response
from fastapi.responses import HTMLResponse,RedirectResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from sqlalchemy.orm import Session
import os

from database import Base, engine, SessionLocal
import models
# from login_book.schemas import UserCreate, ItemIn
import auth

from starlette.exceptions import HTTPException as StarletteHTTPException
from starlette.status import HTTP_404_NOT_FOUND, HTTP_500_INTERNAL_SERVER_ERROR

#--- 앱/템플릿 정적파일 ---

app=FastAPI()

BASE_DIR=os.path.dirname(os.path.abspath(__file__))
templates=Jinja2Templates(directory=os.path.join(BASE_DIR, "templates"))
if os.path.isdir(os.path.join(BASE_DIR,"static")):
    app.mount("/static",StaticFiles(directory=os.path.join(BASE_DIR,"static")),name="static")
# /static : URL 경로
# directory : 실제 파일이 위치한 경로 지정
# name="라우트 이름" : 템플릿에서 URL 생성시 url_for("라우트 이름", path='style.css')

# --- DB 테이블 생성 ---
Base.metadata.create_all(bind=engine)

# --- DB 세션 의존성 ---
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# --- 현재 유저 의존성 (쿠키 기반) ---
def get_current_user(request: Request, db: Session = Depends(get_db)) -> models.User: # models.User 반환 타입
    token = auth.get_token_from_request(request)
    if not token:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Not authenticated")
    payload = auth.decode_token(token)
    if not payload or "sub" not in payload:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid token")
    user = db.query(models.User).filter(models.User.id == int(payload["sub"])).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="User not found")
    return user

# --- 홈: 로그인 페이지로 리다이렉트 ---
@app.get("/", response_class=HTMLResponse)
def home():
    return RedirectResponse(url="/login", status_code=302)

# --- 회원가입 폼 ---
@app.get("/register", response_class=HTMLResponse)
def register_page(request: Request):
    return templates.TemplateResponse("register.html", {"request": request, "title": "회원가입"})

# --- 회원가입 처리 ---
@app.post("/register", response_class=HTMLResponse)
def register(
    request: Request,
    username: str = Form(...),
    password: str = Form(...),
    db: Session = Depends(get_db),
):
    exists = db.query(models.User).filter(models.User.username == username).first()
    if exists:
        return templates.TemplateResponse(
            "register.html",
            {"request": request, "title": "회원가입", "error": "이미 존재하는 사용자명입니다."},
            status_code=400,
        )
    user = models.User(username=username, hashed_password=auth.hash_password(password))
    db.add(user)
    db.commit()
    return RedirectResponse(url="/login?registered=1", status_code=303)

# --- 로그인 폼 ---
@app.get("/login", response_class=HTMLResponse)
def login_page(request: Request, registered: int | None = None):
    return templates.TemplateResponse(
        "login.html",
        {"request": request, "title": "로그인", "registered": registered},
    )

# --- 로그인 처리: JWT를 HttpOnly 쿠키에 저장 ---
@app.post("/login")
def login(
    response: Response,
    username: str = Form(...),
    password: str = Form(...),
    db: Session = Depends(get_db),
):
    user = db.query(models.User).filter(models.User.username == username).first()
    if not user or not auth.verify_password(password, user.hashed_password): # 사용자 존재 & 비밀번호 검증
        return RedirectResponse(url="/login?error=1", status_code=303)
    token = auth.create_access_token(str(user.id)) # JWT 생성 -> 사용자 ID(user.id)를 sub로 사용
    # HttpOnly 쿠키 설정
    response = RedirectResponse(url="/items", status_code=303)
    response.set_cookie(
        key=auth.COOKIE_NAME,
        value=f"Bearer {token}", # JWT토큰을 담은 쿠키
        httponly=True, # HttpOnly 쿠키 : JS에서 접근 불가능, 브라우저와 서버가 통신할때만 전송
        secure=False,  # 로컬 http 개발이면 False, https면 True
        samesite="lax", # CSRF 방어
        max_age=60 * 60, # 쿠키 유효시간 (60*60 초)
    )
    return response

# --- 로그아웃: 쿠키 삭제 ---
@app.post("/logout")
def logout():
    resp = RedirectResponse(url="/login", status_code=303)
    resp.delete_cookie(auth.COOKIE_NAME) # 클라이언트 브라우저에 저장된 쿠키를 제거(Fast API)
    return resp

# --- 아이템 목록 + 생성 폼 (로그인 필요) ---
@app.get("/items", response_class=HTMLResponse)
def items_page(request: Request, db: Session = Depends(get_db), user: models.User = Depends(get_current_user)):
                                                                # 쿠키에서 로그인한 유저의 정보를 꺼냄
    items = db.query(models.Item).filter(models.Item.owner_id == user.id).order_by(models.Item.id.desc()).all()
    return templates.TemplateResponse(
        "items.html",
        {"request": request, "title": "내 아이템", "user": user, "items": items},
    )

# --- 폼으로 Item 생성 ---
@app.post("/items")
def create_item_from_form(
    request: Request,
    name: str = Form(...),
    price: float = Form(...),
    is_offer: str | None = Form(None),  # "true" or ""
    db: Session = Depends(get_db),
    user: models.User = Depends(get_current_user),
):
    item = models.Item(
        name=name,
        price=price,
        is_offer="true" if (is_offer and is_offer.lower() == "true") else None,
        owner_id=user.id,
    )
    db.add(item)
    db.commit()
    return RedirectResponse(url="/items", status_code=303)


# 404 예외 핸들러
@app.exception_handler(StarletteHTTPException)
async def http_exception_handler(request: Request, exc: StarletteHTTPException):
    if exc.status_code == HTTP_404_NOT_FOUND:
        return templates.TemplateResponse("404.html", {"request": request}, status_code=404)
    return templates.TemplateResponse("error.html", {"request": request, "detail": exc.detail}, status_code=exc.status_code)

# 500 예외 핸들러
@app.exception_handler(Exception)
async def internal_exception_handler(request: Request, exc: Exception):
    return templates.TemplateResponse(
        "500.html",
        {"request": request, "detail": str(exc)},
        status_code=HTTP_500_INTERNAL_SERVER_ERROR,
    )

