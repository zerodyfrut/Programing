from tkinter import *
from PIL import Image, ImageTk  # 이미지 크기 조절을 위해 추가
import os

# 이미지 폴더 설정
image_folder = "D:/resource/GIF/"  
image_files = ["jeju1.gif", "jeju2.gif", "jeju3.gif", "jeju4.gif", 
               "jeju5.gif", "jeju6.gif", "jeju7.gif", "jeju8.gif", "jeju9.gif"]

current_index = 0  # 현재 이미지 인덱스

def update_image():
    """현재 이미지 업데이트 (크기 조절 포함)"""
    img_path = os.path.join(image_folder, image_files[current_index])
    img = Image.open(img_path)
    img = img.resize((600, 400), Image.Resampling.LANCZOS)  # 이미지 크기 조절
    photo = ImageTk.PhotoImage(img)
    pLabel.configure(image=photo)
    pLabel.image = photo

def next_image():
    """다음 이미지 표시"""
    global current_index
    current_index = (current_index + 1) % len(image_files)  # 순환 구조
    update_image()

def prev_image():
    """이전 이미지 표시"""
    global current_index
    current_index = (current_index - 1) % len(image_files)  # 순환 구조
    update_image()

# 메인 윈도우 설정
window = Tk()
window.geometry("700x500")
window.title("이미지 뷰어")

# 버튼 및 이벤트 설정
btnPrev = Button(window, text="<< 이전", command=prev_image)
btnNext = Button(window, text="다음 >>", command=next_image)

btnPrev.place(x=250, y=10)
btnNext.place(x=400, y=10)

pLabel = Label(window)
pLabel.place(x=50, y=50)

# 키보드 이벤트 바인딩
window.bind("<Left>", lambda event: prev_image())  # ← 키: 이전 이미지
window.bind("<Right>", lambda event: next_image())  # → 키: 다음 이미지

# 첫 번째 이미지 표시
update_image()

window.mainloop()
