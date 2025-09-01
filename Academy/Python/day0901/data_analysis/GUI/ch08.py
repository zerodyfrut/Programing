import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QAction, QFileDialog, QLabel # pylint: disable=no-name-in-module
from PyQt5.QtGui import QPixmap, QImage # pylint: disable=no-name-in-module
from PyQt5.QtCore import Qt # pylint: disable=no-name-in-module
from PIL import Image, ImageEnhance

class MiniPhotoShop(QMainWindow):
    def __init__(self):
        super().__init__()
        self.initUI()
        self.image_path = None
        self.image = None  # 원본 이미지
        self.modified_image = None  # 편집된 이미지

    def initUI(self):
        """GUI 기본 설정"""
        self.setWindowTitle("Mini Photoshop (PyQt)")
        self.setGeometry(300, 100, 800, 600)

        # 메뉴바 설정
        menubar = self.menuBar()
        fileMenu = menubar.addMenu("파일")
        editMenu = menubar.addMenu("이미지 편집")

        # 파일 열기
        openFile = QAction("파일 열기", self)
        openFile.triggered.connect(self.openFile)
        fileMenu.addAction(openFile)

        # 파일 저장
        saveFile = QAction("파일 저장", self)
        saveFile.triggered.connect(self.saveFile) # 메뉴바 액션은 triggered : 클릭을 통해 활성화 되는 위젝에서 사용
        fileMenu.addAction(saveFile) # clicked는 직접 클릭했을때 발생하는 시그널..

        # 프로그램 종료
        exitApp = QAction("종료", self)
        exitApp.triggered.connect(self.close)
        fileMenu.addAction(exitApp)

        # 이미지 조작 기능 추가
        zoomIn = QAction("확대", self)
        zoomIn.triggered.connect(self.zoomIn)
        editMenu.addAction(zoomIn)

        zoomOut = QAction("축소", self)
        zoomOut.triggered.connect(self.zoomOut)
        editMenu.addAction(zoomOut)

        rotate = QAction("회전", self)
        rotate.triggered.connect(self.rotateImage)
        editMenu.addAction(rotate)

        mirrorH = QAction("좌우 반전", self)
        mirrorH.triggered.connect(self.flipHorizontal)
        editMenu.addAction(mirrorH)

        mirrorV = QAction("상하 반전", self)
        mirrorV.triggered.connect(self.flipVertical)
        editMenu.addAction(mirrorV)

        brightness = QAction("밝게", self)
        brightness.triggered.connect(lambda: self.adjustBrightness(1.2))
        editMenu.addAction(brightness)

        darken = QAction("어둡게", self)
        darken.triggered.connect(lambda: self.adjustBrightness(0.8))
        editMenu.addAction(darken)

        grayscale = QAction("흑백 이미지", self)
        grayscale.triggered.connect(self.convertToGrayscale)
        editMenu.addAction(grayscale)

        # 이미지 표시용 QLabel
        self.imageLabel = QLabel(self)
        self.imageLabel.setAlignment(Qt.AlignCenter) # 중앙 정렬
        self.setCentralWidget(self.imageLabel) # QLabel을 중앙 위젯으로 설정

        self.show()

    def openFile(self):
        """이미지 파일 열기"""
        filePath, _ = QFileDialog.getOpenFileName(self, "파일 열기", "", "이미지 파일 (*.png *.jpg *.jpeg *.bmp *.gif);; 모든 파일 (*.*)")

        if filePath:
            self.image_path = filePath
            self.image = Image.open(filePath).convert("RGB")  # RGB로 변환
            self.modified_image = self.image.copy()
            self.displayImage()

    def saveFile(self):
        """편집된 이미지 저장"""
        if self.modified_image:
            savePath, _ = QFileDialog.getSaveFileName(self, "파일 저장", "", "JPEG 파일 (*.jpg);; PNG 파일 (*.png);; 모든 파일 (*.*)")
            if savePath:
                self.modified_image.save(savePath)
    
    def displayImage(self):
        """현재 편집된 이미지를 GUI에 표시"""
        if self.modified_image:
            img = self.modified_image.convert("RGB")  # RGB 변환
            data = img.tobytes("raw", "RGB")  # 바이트 변환
            qimage = QImage(data, img.width, img.height, QImage.Format_RGB888)
            pixmap = QPixmap.fromImage(qimage)

            self.imageLabel.setPixmap(pixmap)

    def zoomIn(self):
        """이미지 확대"""
        if self.modified_image:
            width, height = self.modified_image.size
            self.modified_image = self.modified_image.resize((int(width * 1.2), int(height * 1.2)), Image.LANCZOS)
            self.displayImage()

    def zoomOut(self):
        """이미지 축소"""
        if self.modified_image:
            width, height = self.modified_image.size
            self.modified_image = self.modified_image.resize((int(width * 0.8), int(height * 0.8)), Image.LANCZOS)
            self.displayImage()

    def rotateImage(self):
        """이미지 회전"""
        if self.modified_image:
            self.modified_image = self.modified_image.rotate(90, expand=True)
            self.displayImage()

    def flipHorizontal(self):
        """좌우 반전"""
        if self.modified_image:
            self.modified_image = self.modified_image.transpose(Image.FLIP_LEFT_RIGHT)
            self.displayImage()

    def flipVertical(self):
        """상하 반전"""
        if self.modified_image:
            self.modified_image = self.modified_image.transpose(Image.FLIP_TOP_BOTTOM)
            self.displayImage()

    def adjustBrightness(self, factor):
        """이미지 밝기 조절"""
        if self.modified_image:
            enhancer = ImageEnhance.Brightness(self.modified_image)
            self.modified_image = enhancer.enhance(factor)
            self.displayImage()

    def convertToGrayscale(self):
        """이미지를 흑백으로 변환"""
        if self.modified_image:
            self.modified_image = self.modified_image.convert("L").convert("RGB")  # 흑백 변환 후 RGB로 변환
            self.displayImage()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MiniPhotoShop()
    sys.exit(app.exec_())
