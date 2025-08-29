import os
import sys

# QT_QPA_PLATFORM_PLUGIN_PATH 환경 변수를 설정하여 플러그인 경로를 명시적으로 지정합니다.
os.environ['QT_QPA_PLATFORM_PLUGIN_PATH'] = r'D:\새 폴더\Programing\Academy\Python\day0829\analysis\Lib\site-packages\PyQt5\Qt5\plugins'


from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QPushButton, QVBoxLayout, QLineEdit, QListWidget
from PyQt5.QtGui import QPalette, QColor

class MyApp(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()
        
    def initUI(self):
        self.setWindowTitle('선착순 가입 프로그램')
        self.setGeometry(300, 300, 400, 300)

        # 배경색 설정
        palette = QPalette()
        palette.setColor(QPalette.Window, QColor("#E6E6FA"))  # 연한 보라색 배경
        self.setPalette(palette)

        # 레이블 생성
        self.label = QLabel('이름을 입력하세요:', self)

        # 입력창 생성
        self.input_text = QLineEdit(self)

        # 리스트 위젯 (가입된 사용자 목록)
        self.list_widget = QListWidget(self)

        # 버튼 생성
        self.button = QPushButton('가입하기', self)
        self.button.setStyleSheet("background-color: #4682B4; color: white; font-weight: bold;")  # 버튼 색상 (스틸블루)
        self.button.clicked.connect(self.on_click)

        # 레이아웃 설정
        vbox = QVBoxLayout()
        vbox.addWidget(self.label)
        vbox.addWidget(self.input_text)
        vbox.addWidget(self.list_widget)
        vbox.addWidget(self.button)

        self.setLayout(vbox)

        # 가입자 리스트
        self.user_list = []

        self.show()

    def on_click(self):
        text = self.input_text.text().strip()  # 입력된 텍스트 가져오기 (앞뒤 공백 제거)
        # 중복 방지 코드 추가
        
        if text:  # 입력값이 비어있지 않다면
            if len(self.user_list) < 5:  # 최대 5명까지 가입 가능
                self.user_list.append(text)
                self.list_widget.addItem(text)  # 리스트 위젯에 추가
                self.input_text.clear()  # 입력창 초기화
                self.label.setText(f"'{text}' 님 가입 완료!")
            else:
                self.label.setText("🚨 선착순 가입 종료 🚨")
                self.button.setEnabled(False)  # 버튼 비활성화
                self.button.setStyleSheet("background-color: gray; color: white;")  # 버튼 색상 변경

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyApp()
    sys.exit(app.exec_())
