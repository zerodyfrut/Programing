from sqlalchemy import Column, Integer, String
from database import Base

class Book(Base): # database.py에 명시된 Base 상속
    __tablename__ = "library" # 테이블명 설정

    # 컬럼명 및 컬럼 설정
    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(255), nullable=False)
    author = Column(String(50), nullable=False)
    year = Column(Integer)
