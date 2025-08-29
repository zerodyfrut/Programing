import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
# pip install plotly
import plotly.express as px

# 차트 한글 깨짐 방지
plt.rcParams['font.family'] = "Malgun Gothic"  # Windows (맑은 고딕)
plt.rcParams['axes.unicode_minus'] = False  # 마이너스(-) 기호 깨짐 방지

# 📌 Streamlit App Title
st.title("📊 데이터 분석 및 차트 시각화")

# 📂 파일 업로드 기능
uploaded_file = st.file_uploader("CSV 파일을 업로드하세요", type=["csv"])

if uploaded_file is not None:
    # 데이터 로드
    df = pd.read_csv(uploaded_file) # csv를 데이터프레임으로 읽기

    # 📌 데이터 미리보기 & 기초 통계 정보 나란히 배치
    col1, col2 = st.columns(2) # 화면에 두개의 열로 나눔

    with col1:
        st.write("### 📝 데이터 미리보기")
        st.dataframe(df.head())

    with col2:
        st.write("### 📈 기초 통계 정보")
        st.dataframe(df.describe())

    # 수치형 컬럼 선택
    numeric_columns = df.select_dtypes(include=['int64', 'float64']).columns.tolist()
    # csv 파일에서 특정 타입의 컬럼들을 list화

    if numeric_columns:
        selected_column = st.selectbox("📊 분석할 수치형 데이터를 선택하세요", numeric_columns)

        # 📊 Matplotlib을 활용한 히스토그램
        fig, ax = plt.subplots()
        ax.hist(df[selected_column], bins=20, color='skyblue', edgecolor='black')
        # bins : 구간 개수
        ax.set_title(f"{selected_column} 분포 히스토그램")
        ax.set_xlabel(selected_column)
        ax.set_ylabel("빈도수")
        st.pyplot(fig)  # Matplotlib 그래프 표시

        # 📊 Plotly를 활용한 인터랙티브 라인 차트
        fig2 = px.line(df, x=df.index, y=selected_column, title=f"{selected_column} 변화 추이")
        # 이건 시간에 따른 변화가 좋을거같은데.
        st.plotly_chart(fig2)  # Plotly 그래프 표시

    else:
        st.warning("⚠️ 데이터에 수치형 컬럼이 없습니다. CSV 파일을 확인하세요.")


# streamlit run stream_ex05.py