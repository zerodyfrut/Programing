import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QPushButton, QVBoxLayout
from PyQt5.QtGui import QPalette, QColor

class MyApp(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        # 기본 배경색 설정
        self.setWindowTitle('이벤트 처리 예제')
        self.setGeometry(300, 300, 300, 200)

        # 레이블 생성
        self.label = QLabel('버튼을 눌러보세요!', self)

        # 버튼 생성
        self.button = QPushButton('클릭하세요', self)
        self.button.setStyleSheet("background-color: #4682B4; color: white; font-weight: bold;")  # 버튼 색상 설정
        self.button.clicked.connect(self.on_button_click)  # 버튼 클릭 이벤트 연결

        # 레이아웃 설정
        vbox = QVBoxLayout()
        vbox.addWidget(self.label)
        vbox.addWidget(self.button)
        self.setLayout(vbox)

        self.show()

    def on_button_click(self):
        """버튼 클릭 시 레이블 텍스트 변경 및 배경색 변경"""
        self.label.setText('🎉 환영합니다! 🎉')  # 환영 메시지 표시
        
        # 배경색 변경
        palette = QPalette()
        palette.setColor(QPalette.Window, QColor("#FFD700"))  # 배경색을 금색으로 변경
        self.setPalette(palette)

        # 버튼 스타일 변경 (클릭 후 색상 변화)
        self.button.setStyleSheet("background-color: #32CD32; color: white; font-weight: bold;")  # 초록색으로 변경
        self.button.setText("변경 완료!")  # 버튼 텍스트 변경

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyApp()
    sys.exit(app.exec_())
