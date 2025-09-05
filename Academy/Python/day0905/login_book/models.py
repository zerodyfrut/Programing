from sqlalchemy import Column,Integer,String,ForeignKey,Numeric
from sqlalchemy.orm import relationship
from database import Base

class User(Base):
    __tablename__='users'
    id=Column(Integer,primary_key=True,index=True)
    username=Column(String(50),unique=True,nullable=False,index=True)
    hashed_password=Column(String(255),nullable=False)
    items=relationship("Item",back_populates="owner",cascade="all,delete-orphan")

class Item(Base):
    __tablename__='items'
    id=Column(Integer,primary_key=True,index=True)
    name=Column(String(255),nullable=False)
    price=Column(Numeric(10,2),nullable=False)
    is_offer=Column(String(5),nullable=True) # "true"/None (간단화)
    owner_id=Column(Integer,ForeignKey=("users.id"),nullable=False)

    owner=relationship("User",back_populates="items")

    