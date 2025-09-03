from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base


# MySQL 연결 정보
DATABASE_URL='mysql+pymysql://ptest:pytest@localhost:3306/memo_app'
engin=create_engine(DATABASE_URL,echo=True)
SessionLocal=sessionmaker(autocommit=False,autoflush=False,bind=engin)
Base=declarative_base()