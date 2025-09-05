#(JWT & 비밀번호 해시 + 쿠키 유틸)
from datetime import datetime,timedelta,timezone
from typing import Optional
from jose import jwt,JWTError
from passlib.context import CryptContext
from fastapi import Request

SECRET_KEY="change-this-to-very-secret" # 실제 환경에서는 환경 변수로!
ALGORITHM="HS256"
ACCESS_TOKEN_EXPITRE_MINUTES=60

pwd_context=CryptContext(schemes=['bcrypt'],deprecated='auto')

def hash_password(password:str) ->str:
    return pwd_context.hash(password)

def verify_password(plain:str,hashed:str)->bool:
    return pwd_context.verify(plain,hashed)

def create_access_token(sub:str)->str:
    expire=datetime.now(timezone.utc)+timedelta(minutes=ACCESS_TOKEN_EXPITRE_MINUTES)
    payload={"sub":sub,"exp":expire}
    return jwt.encode(payload,SECRET_KEY,algorithm=ALGORITHM)

def decode_token(token:str)->Optional[dict]:
    try:
        return jwt.decode(token, SECRET_KEY,algorithms=ALGORITHM)
    except JWTError:
        return None
    
# 쿠키에서 bearer 토큰 꺼내기 (dev 용, HttpOnly)
COOKIE_NAME="access_token"

def get_token_form_request(request:Request)->Optional[str]:
    token=request.cookies.get(COOKIE_NAME)
    # "Bearer xxx" 형태로 저장했다면 접두어 제거
    if token and token.lower().startswith("bearer "):
        return token.split(" ",1)[1]
    return token
