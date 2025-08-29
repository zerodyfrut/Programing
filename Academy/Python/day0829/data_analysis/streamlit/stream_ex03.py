import streamlit as st
import numpy as np
import matplotlib.pyplot as plt

# 차트 한글깨짐 방지
plt.rcParams['font.family']='Malgun Gothic' # window (맑은 고딕)
plt.rcParams['axes.unicode_minus']=False # 마이너스(-)기호 깨짐 방지
# 유니코드의 - 기호가 아니라 아스키 코드의 -를 사용

st.title('Streamlit 시각화 적용📊')
number=st.number_input('숫자를 입력하세요.',min_value=1.0,max_value=100.0,value=10.0,step=0.1)
# value : 초기값

st.write(f'입력한 숫자의 제곱 : {number**2}')

x=np.linspace(0,number,100) # linespace(시작값, 끝값, 생성 데이터 수)
                            # 10 입력시 0부터 10까지 99등분해 100개의 데이터 생성
y=x**2 

fig, ax=plt.subplots()
ax.plot(x,y,label='y=x^2',color='blue',linestyle='-.') # 선 그래프
ax.set_xlabel('X값')
ax.set_ylabel('Y값')
ax.legend()

st.pyplot(fig) # matplotlib 그래프 출력


# streamlit run stream_ex03.py