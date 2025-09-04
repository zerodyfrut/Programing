from pydantic import BaseModel
# FastAPI 에서 DB모델과 요청/응답 데이터를 안전하게 검증
# JSON -> Note

class NoteBase(BaseModel): # 메모의 공통 필드 정의
    title: str             # title, content에 문자열이 잘 들어왔는지 검사
    content: str

class NoteCreate(NoteBase): # 메모에 새로운 레코드 생성시 (POST 요청시)
    pass

class NoteUpdate(NoteBase): # 기존 메모 수정시 (PUT 요청시)
    pass

class NoteOut(NoteBase): # 응답용 스키마 : DB에서 가져온 데이터를 반환시 사용 (GET 응답시)
    id: int              # id 필드 int 값인지 검사

    class Config:
        orm_mode = True # sqlalchemy객체 -> 검사용 padantic객체로 변환 가능


# 검사 실패시 422 Unprocessable Entity 응답 발생


# Json으로 응답받고 할때 가능

# @app.post("/notes/", response_class=HTMLResponse)
# def create_note(
#     note:NoteCreate,
#     db: Session = Depends(get_db)
# ):


# response_model=NoteOut