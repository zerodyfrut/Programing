from tkinter import *
window = Tk()
window.geometry("500x500")
window.title("반려동물 선택하기")


#함수
def myFunc():
    if var.get() == 1:
        # var 값이 1이면
        labelImage.configure(image=photo1) #configure : 속성값을 동적으로 변경
        # labelImage에 해당하는 이미지를 넣음
    elif var.get() ==2 :
        labelImage.configure(image=photo2)
    else :
        labelImage.configure(image=photo3)

#메인

labelText=Label(window, text="좋아하는 반려동물 투표", fg="blue", font=("궁서체",20))
# label 설정, window창에 출력

var=IntVar() # int 값 변수
rb1=Radiobutton(window, text="강아지", variable=var, value=1)
# window창에 라디오버튼 생성, text는 강아지, 변수 var에 값1을 넣는다.
rb2=Radiobutton(window, text="고양이", variable=var, value=2)
rb3=Radiobutton(window, text="토끼", variable=var, value=3)
buttonOK=Button(window, text="사진보기", command=myFunc)
# window창에 버튼 생성, text는 사진보기, myFunc 함수 실행, command에 onClick 이런 이벤트 종류는 안넣나?

photo1=PhotoImage(file="D:/resource/GIF/dog4.gif")
photo2=PhotoImage(file="D:/resource/GIF/cat.gif")
photo3=PhotoImage(file="D:/resource/GIF/rabbit.gif")
# D드라이브 resource에 GIF 폴더 있는 gif 파일들

labelImage=Label(window, width=200, height=200 ,bg='yellow',image=None)
# labelImage의 크기, 처음 사진 지정

## =============================================


labelText.pack(padx=5, pady=5)
rb1.pack(padx=5, pady=5)
rb2.pack(padx=5, pady=5)
rb3.pack(padx=5, pady=5)
buttonOK.pack(padx=5, pady=5)
labelImage.pack(padx=5, pady=5)
#이미지의 padding 값 지정

window.mainloop()
