from pymongo import MongoClient

# 1. MongoDB 연결
client = MongoClient("mongodb://localhost:27017/")
db = client["sampleDB"]
users = db["users"]

# 2. 기존 데이터 정리(테스트 환경용)
users.delete_many({})

# 3. 샘플데이터 10개 삽입
sample_users=[
    {"name":"Kim Minsoo","email":"minsoo.kim@example.com","age":28,"skills":["Python","MongoDB"]},
    {"name": "Park Jisoo","email": "jisoo.park@work.net","age": 32, "skills": ["Java", "SQL"]},
    {"name": "Lee Hana","email": "hana.lee@gmail.com","age": 24, "skills": ["Python", "Flask"]},
    {"name": "kim Doyeon","email": "doyeon.kim@company.com","age": 29, "skills": ["React", "Node.js"]},
    {"name": "Choi Seungmin","email": "seungmin.choi@naver.net","age": 35, "skills": ["C#", "MSSQL"]},
    {"name": "Park Sungho","email": "sungho.park@service.com","age": 41, "skills": ["Go", "MongoDB"]},
    {"name": "Yoon Ara","email": "ara.yoon@example.org","age": 22, "skills": ["Python", "Django"]},
    {"name": "Han Byul","email": "byul.han@test.com","age": 27, "skills": ["Rust", "PostgreSQL"]},
    {"name": "Jung Woo","email": "woo.jung@sample.net","age": 30, "skills": ["C++", "Python"]},
    {"name": "Park Minji","email": "minji.park@domain.com", "age": 26, "skills": ["Kotlin", "Android"]}
]

users.insert_many(sample_users)



client.close()