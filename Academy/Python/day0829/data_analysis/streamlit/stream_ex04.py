import streamlit as st
import pandas as pd

st.title("Streamlit 도시 선택 📊")

# saple data
data={
    '이름':['철수','영희','민수','지수','태현'],
    '나이':[25,30,22,27,29],
    '도시':['서울','부산','대전','광주','서울']
}
df=pd.DataFrame(data)
# print(type(df['도시'])) # Series 타입

# 도시 선택 필터
selected_city= st.selectbox("도시를 선택하세요 : ",df['도시'].unique())
# unique() : 중복값 제거
# 1차원 배열이면 다 되는듯. 리스트,튜플,셋

# 선택한 도시의 데이터만 표시
filtered_df= df[df['도시']==selected_city]

st.write(f'**{selected_city}에 거주하는 사람들**')
st.dataframe(filtered_df) # dataframe(df) : streamlit 에서 데이터프레임 출력 : 인터렉티브 테이블
# st.write(filtered_df)

# streamlit run stream_ex04.py