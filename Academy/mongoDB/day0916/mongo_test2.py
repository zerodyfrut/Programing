from pymongo import MongoClient
# pip install pymongo

# 로컬 MongoDB 연결 (기본 포트 27017)
client = MongoClient("mongodb://localhost:27017/")

# DB 안에 컬렉션안에 Document
# 데이터베이스 선택 (없으면 자동 생성)
db = client["testDB"]

# 컬렉션 선택 (없으면 자동 생성)
users = db["users"]

count =users.count_documents({})
print(count)

count_age=users.count_documents({"age":{"$gte":30}})
print(count_age)

s_names=users.distinct("skills")
print(s_names)

client.close()
