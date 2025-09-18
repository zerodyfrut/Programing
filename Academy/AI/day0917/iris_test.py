from sklearn import datasets
from sklearn import svm
import plotly.express as px

d=datasets.load_iris() # iris 데이터셋을 읽고
print(d.DESCR) # 내용을 출력(description)

for i in range(0,len(d.data)): # 샘플을 순서대로 출력
    print(i+1,d.data[i],d.target[i])


s=svm.SVC(gamma=0.1,C=10) # svm 분류 모델 SVC 객체 생성하고
# 선으로 그룹을 나눔
s.fit(d.data,d.target) # iris 데이터로 학습

new_d = [[5.1,3.8,1.9,0.4], [6.2,3.4,5.4,2.3]]

res = s.predict(new_d)
print("새로운 2개 샘플의 레이블 :", res)


df = px.data.iris()
fig = px.scatter_3d(df, x='sepal_length', y='sepal_width', z='petal_width', color='species') # petal_length를 제외하여 3차원 공간 구성
fig.show(renderer="browser")

