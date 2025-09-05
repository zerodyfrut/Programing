from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker,declarative_base

# MySQL 연결정보
DATABASE_URL="mysql+pymysql://pytest:pytest@localhost:3306/memo_app"

engine=create_engine(DATABASE_URL,echo=False,
                     pool_pre_ping=True,
                     future=True)

SessionLocal=sessionmaker(autocommit=False,autoflush=False,bind=engine)

Base=declarative_base()