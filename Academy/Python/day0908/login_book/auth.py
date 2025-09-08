#(JWT & 비밀번호 해시 + 쿠키 유틸)
from datetime import datetime,timedelta,timezone
from typing import Optional
from jose import jwt,JWTError
from passlib.context import CryptContext # bcypt, argon2 등의 알고리즘 지원
from fastapi import Request

SECRET_KEY="change-this-to-very-secret" # 실제 환경에서는 환경 변수로!
ALGORITHM="HS256" # 사용할 알고리즘
ACCESS_TOKEN_EXPITRE_MINUTES=60 # 토큰의 유효시간 60분

pwd_context=CryptContext(schemes=['bcrypt'],deprecated='auto')
# 'bcrypt' : 해싱 알고리즘 사용
# deprecated='auto' : 자동으로 최신 알고리즘 사용

# 비밀번호 해싱(bcrypto)
def hash_password(password:str) ->str:
    return pwd_context.hash(password)

# 비밀번호와 DB에 저장된 해시를 비교
def verify_password(plain:str,hashed:str)->bool:
    return pwd_context.verify(plain,hashed)
# 해싱된 DB 비밀번호와 form의 비밀번호를 해싱해서 비교

# JWT 생성
def create_access_token(sub:str)->str:
    expire=datetime.now(timezone.utc)+timedelta(minutes=ACCESS_TOKEN_EXPITRE_MINUTES)
    payload={"sub":sub,"exp":expire}
    return jwt.encode(payload,SECRET_KEY,algorithm=ALGORITHM)

# 디코드
def decode_token(token:str)->Optional[dict]:
    try:
        return jwt.decode(token, SECRET_KEY,algorithms=ALGORITHM)
    except JWTError: # 유효시간이 만료, 변조가 시 JWT에러 발생
        return None

# 쿠키에서 bearer 토큰 꺼내기 (dev 용, HttpOnly)
COOKIE_NAME="access_token"

def get_token_from_request(request:Request)->Optional[str]:
    token=request.cookies.get(COOKIE_NAME)
    # "Bearer xxx" 형태로 저장했다면 접두어 제거
    if token and token.lower().startswith("bearer "):
        return token.split(" ",1)[1]
    return token
