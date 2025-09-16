from pymongo import MongoClient
# pip install pymongo

# 로컬 MongoDB 연결 (기본 포트 27017)
client = MongoClient("mongodb://localhost:27017/")

# DB 안에 컬렉션안에 Document
# 데이터베이스 선택 (없으면 자동 생성)
db = client["testDB"]

# 컬렉션 선택 (없으면 자동 생성)
users = db["users"]

users.delete_many({})

# 단일 문서 삽입
user_id = users.insert_one({"name": "Nia", "age": 28, "skills": ["Python", "JavaScript"]}).inserted_id
print("삽입된 _id:", user_id)

# 여러 개 삽입
users.insert_many([
    {"name": "Elisa", "age": 22},
    {"name": "Aiden", "age": 30, "skills": ["Java", "Spring"]}
])

# 전체 문서 조회
for doc in users.find():
    print(doc)

# 조건 검색
result = users.find_one({"name": "Nia"}) 
# find_one 이기 때문에 같은 조건이면 먼저 나온 것을 출력
print("단일 검색:", result)

# 특정 필드만 출력 (projection)
for doc in users.find({}, {"_id": 0, "name": 1}):
    print(doc)
# 값이 0이면 제외, 1이면 포함

# 단일 문서 업데이트
users.update_one({"name": "Nia"}, {"$set": {"age": 29}})
# 앞에는 조건, 뒤에는 변경 내용

# 여러 문서 수정
users.update_many({"age": {"$gte": 25}}, {"$push": {"skills": "MongoDB"}})
# 나이가 25이상인 데이터에 값을 추가

# 특정 문서 삭제
users.delete_one({"name": "Elisa"})

# 여러 문서 삭제
users.delete_many({"age": {"$lt": 25}})

client.close()
