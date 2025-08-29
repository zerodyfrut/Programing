import os
import sys

# QT_QPA_PLATFORM_PLUGIN_PATH 환경 변수를 설정하여 플러그인 경로를 명시적으로 지정합니다.
os.environ['QT_QPA_PLATFORM_PLUGIN_PATH'] = r'D:\새 폴더\Programing\Academy\Python\day0829\analysis\Lib\site-packages\PyQt5\Qt5\plugins'

from PyQt5.QtWidgets import QApplication, QWidget



class MyApp(QWidget): # 기본적인 창을 생성하는 위젯
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('PyQt 기본 창')  # 창 제목 설정
        self.setGeometry(300, 300, 400, 300)  # 창 위치 및 크기 설정(x,y,w,h)

if __name__ == '__main__':
    app = QApplication(sys.argv)  # 애플리케이션 실행을 위한 객체 생성
    ex = MyApp()  # MyApp 클래스의 인스턴스 생성
    ex.show()  # 윈도우 창 표시
    sys.exit(app.exec_())  # 이벤트 루프 실행
