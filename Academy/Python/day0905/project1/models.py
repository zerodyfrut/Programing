from sqlalchemy import Column, Integer, String, ForeignKey, Numeric
from sqlalchemy.orm import relationship
from database import Base

class Book(Base):
    __tablename__ = "books"
    # 정수형 컬럼이 단일 pk인 경우 auto_increment가 자동 적용
    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(50), unique=True, nullable=False, index=True)
    author = Column(String(255), nullable=False)
    year = Column(Integer, nullable=True)
        # 관계 설정 (Book: Review=1:Many)
    reviews=relationship("Review",back_populates="book",cascade="all,delete-orphan")
    # Review와 연결(관계 설정)
    #   relationship("연결 클래스명", 참조 , 연쇄작용)
    #   연결할클래스명(문자로 작성해야 나중에 생성해도 문제가 생기지 않음)
    # back_populates="book" : 양방향 참조가능
    # back_populates="book" : 리뷰가 속한 책
    # casecade 연쇄동작 - all : 부모의 변경사항이 자식에게도 적용
    # delete-orphan : 부모가 없어진 자식은 자동삭제

class Review(Base):
    __tablename__="reviews"

    id=Column(Integer,primary_key=True,index=True)
    content=Column(String(255),nullable=False)
    rating=Column(Integer,nullable=False)
    book_id=Column(Integer,ForeignKey("books.id")) # ForeignKey 처리

    # 역참조
    book = relationship("Book",back_populates="reviews")
    # back_populates="reviews" : 해당 책의 모든 리뷰
