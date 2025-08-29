import tkinter as tk

# 윈도우 생성
window = tk.Tk()
window.title("tkinter 위젯 예제")
window.geometry("400x400")

# 레이블
label = tk.Label(window, text="Hello, tkinter!", font=("Arial", 14))
# 어떤 창에 넣을지 매개변수 설정
label.pack(pady=10) # 레이블 배치 및 상하여백 설정,  padx -> 좌우 여백

# 입력창 (Entry)
entry = tk.Entry(window)
entry.pack(pady=10)

# 리스트박스
listbox = tk.Listbox(window)
listbox.pack(pady=10)

# 버튼 클릭 이벤트 함수
def on_click():
    text = entry.get()  # 입력창에서 내용 가져오기
    if text:  # 입력값이 비어있지 않다면
        listbox.insert(tk.END, text)  # 리스트박스에 추가
                                      # tk.END : 끝위치를 가리키는 상수
        entry.delete(0, tk.END)  # 입력창 비우기, 처음부터 마지막 위치까지
        label.config(text=f"'{text}' 추가됨!")  # 레이블 업데이트

# 버튼
button = tk.Button(window, text="수강 신청", command=on_click)
button.pack(pady=10)

# 체크박스
check_var = tk.IntVar() # 체크박스와 라디오버튼의 상태를 저장할 변수(정수값 저장)
# tk.StringVar(), tk.BooleanVar()...
checkbox = tk.Checkbutton(window, text="강의등록 확인", variable=check_var)
checkbox.pack(pady=10)

# 실행
window.mainloop()
