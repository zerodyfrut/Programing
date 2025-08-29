import streamlit as st
st.title('Streamlit 기본 예제 2 ✍️')

user_name=st.text_input('이름을 입력하세요 : ') # 텍스트 입력받기

if user_name:
    st.write(f'반갑습니다. {user_name}님!🎉')


# streamlit run stream_ex02.py