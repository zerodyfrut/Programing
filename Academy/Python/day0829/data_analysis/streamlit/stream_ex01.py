import streamlit as st

st.title('Stream 기본 예제 1🚀') # <h1>로 제목을 표시
st.write('안녕하세요! streamlit을 배우고 있습니다.') # write: 일반 텍스트

if st.button('클릭해보세요.'): # button : 해당 텍스트를 가진 버튼 추가, 눌리면 True
    st.write('버튼이 눌렸습니다.')


# streamlit run stream_ex01.py