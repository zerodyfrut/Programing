from pymongo import MongoClient

client = MongoClient("mongodb://localhost:27017/")
db = client["sampleDB"]
users = db["users"]

page_size=3
page_number=2

print("----------나이 기준 오름차순 정렬----------")
asc_age=users.find({}).sort("age",1)
for u in asc_age:
    print(u["name"],":",u["age"])

print("----------나이 기준 내림차순 정렬----------")
desc_age=users.find({}).sort("age",-1)
for u in desc_age:
    print(u["name"],":",u["age"])


asc_age_paging=users.find({}).sort("age",1).skip(page_size*(page_number-1)).limit((page_size))
# 현재 페이지를 2로 되어있기 때문에, 1번의 1-3 번 doc를 skip
# limit : 지정한 갯수 가져옴
for u in asc_age_paging:
    print(u)

for page in range(1,5):
    cursor=users.find({}).sort("age",1).skip(page_size*(page-1)).limit(page_size)
    print(f"\n-----Page {page}-----")
    for doc in cursor:
        print(doc)

client.close()
