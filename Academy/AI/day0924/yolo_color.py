from ultralytics import YOLO
import cv2
import time

cap = cv2.VideoCapture(0)
model = YOLO("yolov8n.pt") # 자동 다운로드

while True:
    ret, frame = cap.read()
    if not ret:
        break
    
    results = model.predict(source=frame, conf=0.2) 
    box = results[0].plot()
    
    person_detect = any(int(box.cls[0])==0 for box in results[0].boxes) # person class id=0
    if person_detect:
        cv2.putText(
            box, 'DANGER!! PERSON DETECT!!',(50,50),
        cv2.FONT_HERSHEY_SIMPLEX,2,(0,0,255), 3, cv2.LINE_AA
        )      
    
    cv2.imshow('yolo', box)
    if cv2.waitKey(1) & 0XFF == ord('q'):    
        break
    time.sleep(0.1)
    
cap.release()
cv2.destroyAllWindows()