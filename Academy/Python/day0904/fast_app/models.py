from sqlalchemy import Column, Integer, String, Text
from database import Base

class Note(Base): # database.py에 명시된 Base 상속
    __tablename__ = "notes" # 테이블명 설정

    # 컬럼명 및 컬럼 설정
    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(255), nullable=False)
    content = Column(Text, nullable=False)
