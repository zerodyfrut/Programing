import streamlit as st

st.markdown("# 제목 1")
st.markdown("## 제목 2")
st.markdown("**굵게** / *기울임* / `코드`")
st.markdown("- 리스트 1\n- 리스트 2")
st.markdown("[링크](https://www.streamlit.io)")

#문자열에 마크 다운 문법이 포함되어 있으면 일부 적용됨
st.write("**굵은 글씨**")
st.write("*기울임*")

st.markdown('<b>굵은 글씨2</b>',unsafe_allow_html=True)
st.markdown('<i>이탤릭체</i>', unsafe_allow_html=True)


# streamlit run text.py