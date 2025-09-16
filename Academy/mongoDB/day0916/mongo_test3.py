from pymongo import MongoClient

client = MongoClient("mongodb://localhost:27017/")
db = client["sampleDB"]
users = db["users"]


# 25보다 작지도 않고, 이름도 Lee Hana도 아닌 사용자의 이름과 나이
# {"_id":0,"name":1,"age":1} : # _id 제외(0), name, age만 보여줌(1)
print("----------25보다 작지도 않고, 이름도 Lee Hana도 아닌 사용자의 이름과 나이----------")
nor_user=users.find({"$nor":[{"age":{"$lt":25}}, {"name":"Lee Hana"}]},{"_id":0,"name":1,"age":1})
for nu in nor_user:
    print(nu)


print("----------나이가 20세 이상, 30세 이하인 사용자----------")
# 나이가 20세 이상, 30세 이하인 사용자
users_age = users.find({"$and":[{"age":{"$gte":20}} , {"age":{"$lte":30}}]} , {"_id":0,"name":1,"age":1})

for user in users_age:
    print(user)


print("----------skills 배열에 'Python'이 포함된 사용자----------")
# skills 배열에 "python"이 포함된 사용자
user_skill=users.find({"skills": {"$in":["Python"]}},{"_id":0,"name":1,"skills":1})
user_skill2=users.find({"skills":"Python"},{"_id":0,"name":1,"skills":1})

for user in user_skill:
    print(user)
print("----------skills 배열에 'Python'이 포함된 사용자2----------")
for user in user_skill2:
    print(user)


print("----------이름이 'Kim'으로 시작하는 사용자----------")
# 이름이 "Kim"으로 시작하는 사용자
for doc in users.find({"name":{"$regex":"^Kim"}},{"_id":0,"name":1}):
    print(doc)


print("----------이름에 'park'이 포함된 사용자(대소문자 무시)----------")
# 이름에 "park"이 포함된 사용자(대소문자 무시)
for doc in users.find({"name":{"$regex":"park","$options":"i"}},{"_id":0,"name":1}):
    print(doc)

print("----------이메일이 '.com' 또는 '.net' 으로 끝나는 사용자----------")
# 이메일이 ".com" 또는 ".net" 으로 끝나는 사용자
# 앞에 r을 붙여 이스케이프가 아님을 표현
for doc in users.find({"email":{"$regex":r"\.(com|net)"}},{"_id":0,"name":1,"email":1}):
    print(doc)



client.close()