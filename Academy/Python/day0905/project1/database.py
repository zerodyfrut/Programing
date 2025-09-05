from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# MySQL 연결 정보
DATABASE_URL = "mysql+pymysql://pytest:pytest@localhost:3306/memo_app"
            # mysql+pymysql://계정:비번@DB호스트:포트/데이터베이스명

engine = create_engine(DATABASE_URL, # DB와 실제로 통신하는 객체
    echo=False, # 터미널창에 sql문을 띄우지 않음.
    pool_pre_ping=True, 
    # 커넥션 풀에 커넥션을 여러개 두고 사용&반납을 하는데, 간단한 sql을 보내서 커넥션 유효성 검사(select 1(ping 쿼리))
    # 반필수적으로 넣어주는게 좋음
    future=True,) # alchemy 쓸때 쓰는게 좋다는데.

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine) # DB와 상호작용할 세션(Session) 생성
                                                                            # autocommit=False → commit 수동 처리    
                                                                            # autoflush=False → 변경사항 DB반영 비활성화

Base = declarative_base() #ORM 모델 클래스의 공통 부모

