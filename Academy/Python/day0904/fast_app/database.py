from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# MySQL 연결 정보
DATABASE_URL='mysql+pymysql://pytest:pytest@localhost:3306/memo_app'
            # db 종류에 따라 ://계정:비번@DB호스트:포트/데이터베이스명

engine=create_engine(DATABASE_URL,echo=True) # DB와 실제로 통신하는 객체

SessionLocal=sessionmaker(autocommit=False,autoflush=False,bind=engine) # DB와 상호작용할 세션 생성
                                                                # autocommit : False -> commit 수동 처리
                                                                # autoflush : 변경사항 DB 반영 비활성화

Base=declarative_base() # ORM 모델 클래스의 공통 부모
