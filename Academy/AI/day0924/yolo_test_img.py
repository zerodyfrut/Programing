import cv2
from ultralytics import YOLO
import random


# 1. 모델 불러오기 (사전학습된 YOLOv8n)
model = YOLO("yolov8n.pt")  # 작은 모델 (빠름). yolov8s.pt, yolov8m.pt 도 가능

# 2. 이미지 읽기
img_path = "./f.jpg"
img = cv2.imread(img_path)

colors = {}
for i in range(len(model.names)):
    colors[i] = (random.randint(0, 255),
                 random.randint(0, 255),
                 random.randint(0, 255))


# 3. 예측 수행
results = model(img) # 검출 & 분류

# 4. 결과 처리
for r in results:
    boxes = r.boxes  # 바운딩 박스들
    for box in boxes:
        # 좌표
        x1, y1, x2, y2 = box.xyxy[0].cpu().numpy().astype(int)
        # 신뢰도
        conf = float(box.conf[0])
        if conf < 0.5:   # 0.5 미만은 스킵
            continue
        # 클래스 ID
        cls_id = int(box.cls[0])
        # 클래스 이름
        cls_name = model.names[cls_id]

        color = colors[cls_id]

        # 박스 그리기
        cv2.rectangle(img, (x1, y1), (x2, y2), color, 2)
        # 라벨 텍스트
        label = f"{cls_name} {conf:.2f}"
        cv2.putText(img, label, (x1, y1 - 10),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.6, color, 2)

        # 콘솔 출력
        print(f"검출: {cls_name}, 신뢰도: {conf:.2f}, 박스: [{x1}, {y1}, {x2}, {y2}]")

# 5. 결과 이미지 보여주기
cv2.imshow("YOLOv8 Detection", img)
cv2.waitKey(0)
cv2.destroyAllWindows()
