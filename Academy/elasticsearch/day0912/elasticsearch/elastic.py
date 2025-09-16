from fastapi import FastAPI, Depends, HTTPException
from contextlib import asynccontextmanager
from elasticsearch import AsyncElasticsearch
#pip install "elasticsearch[async]"   ## extras 옵션

#앱의 생명주기 전체를 감싸는 컨텍스트 매니저

@asynccontextmanager
async def lifespan(app: FastAPI):
    # startup: 리소스 초기화
    es_client = AsyncElasticsearch("http://localhost:9200")  # 아마 주소랑 연결하는거같고
    app.state.es_client = es_client  
    print("Elasticsearch 연결됨")
    #yield 앞 부분은 앱 시작 시 실행 (startup)
    yield  # <-- 여기서 앱이 실행됨
    #yield 뒤 부분은 앱 종료 시 실행 (shutdown)
    # shutdown: 리소스 정리
    await es_client.close()
    print("Elasticsearch 연결 종료")

app = FastAPI(lifespan=lifespan)


# 의존성 주입 함수
async def get_es() -> AsyncElasticsearch:
    return app.state.es_client


@app.get("/search")
async def search(q: str, es: AsyncElasticsearch = Depends(get_es)):
    result = await es.search(
        index="book",
        query={"match": {"title": q}}
    )
    return result


@app.post("/add")
async def add_doc(doc: dict, es: AsyncElasticsearch = Depends(get_es)):
    res = await es.index(index="my_index", document=doc)
    return res

# ---------------- 1. 정치 뉴스 태그별 집계 ----------------
@app.get("/agg/politics/tags")
async def politics_tags(es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "size": 0,
        "query": {"term": {"section": "IT"}},
        "aggs": {"tags_count": {"terms": {"field": "tags", "size": 20}}}
    }
    res = await es.search(index="news_example", body=body)
    return res["aggregations"]["tags_count"]["buckets"]

# ---------------- 2. 스포츠 뉴스 중 “승리” 키워드 ----------------
@app.get("/search/sports/victory")
async def sports_victory(es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "query": {
            "bool": {
                "must": [
                    {"term": {"section": "스포츠"}},
                    {"match": {"comment": "승리"}}
                ]
            }
        }
    }
    res = await es.search(index="news_example", body=body)
    return [hit["_source"] for hit in res["hits"]["hits"]]

# ---------------- 3. 특정 섹션 내 태그별 집계 (예: 문화) ----------------
@app.get("/agg/section/{section_name}/tags")
async def section_tags(section_name: str, es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "size": 0,
        "query": {"term": {"section": section_name}},
        "aggs": {"tags_count": {"terms": {"field": "tags", "size": 10}}}
    }
    res = await es.search(index="news_example", body=body)
    return res["aggregations"]["tags_count"]["buckets"]

# ---------------- 4. 모든 뉴스의 태그별 문서 수 집계 ----------------
@app.get("/agg/all/tags")
async def all_tags(es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "size": 0,
        "aggs": {"tags_count": {"terms": {"field": "tags", "size": 20}}}
    }
    res = await es.search(index="news_example", body=body)
    return res["aggregations"]["tags_count"]["buckets"]

# ---------------- 5. 경제 뉴스에서 “금리” 키워드 ----------------
@app.get("/search/economy/interest")
async def economy_interest(es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "query": {
            "bool": {
                "must": [
                    {"term": {"section": "경제"}},
                    {"match": {"comment": "금리"}}
                ]
            }
        }
    }
    res = await es.search(index="news_example", body=body)
    return [hit["_source"] for hit in res["hits"]["hits"]]

# ---------------- 6. 정치 뉴스만 검색 ----------------
@app.get("/search/politics")
async def search_politics(es: AsyncElasticsearch = Depends(get_es)):
    body = {"query": {"term": {"section": "정치"}}}
    res = await es.search(index="news_example", body=body)
    return [hit["_source"] for hit in res["hits"]["hits"]]

@app.get("/")
async def index(es: AsyncElasticsearch = Depends(get_es)):
    body = {
        "size":100,
        "query": {"match_all": {}},   # 문서 조회용 쿼리
    }
    res = await es.search(index="news_example", body=body)

    docs = [{"id": hit["_id"], **hit["_source"]} for hit in res["hits"]["hits"]]
    return {"total": res["hits"]["total"]["value"], "documents": docs}





#----------------- CRUD---------------------

@app.get("/newsadd")
async def create_news(es: AsyncElasticsearch = Depends(get_es)):
    news =  {"section": "IT", "comment": "아이폰 17 오늘부터 사전예약 시작…통신사 혜택 풍성", "tags": ["통신사","혜택","아이폰"]}

    res = await es.index(index="news_example", document=news)
    return {"result": res["result"], "id": res["_id"]}


# @app.post("/newsUpdate/{}")
# async def update_news(es:AsyncElasticsearch,doc_id:str,update_field:dict):

#     id=res["_id"]
#     news={}
#     res=await es.update(index="news_example")

async def update_news_by_id(es: AsyncElasticsearch, doc_id: str, updated_fields: dict):
    """
    _id로 문서를 찾아서 일부 필드를 수정
    """
    try:
        await es.update(
            index="news_example",
            id=doc_id,
            doc=updated_fields
        )
        updated_doc = await es.get(index="news_example", id=doc_id)
        return updated_doc["_source"]
    
    except Exception as e:
        raise HTTPException(status_code=404, detail=f"Document not found: {doc_id}")

@app.post("/news/{doc_id}")
async def update_news(doc_id: str, comment: str, es: AsyncElasticsearch = Depends(get_es)):
    updated_fields = {"comment": comment}
    updated_doc = await update_news_by_id(es, doc_id, updated_fields)  # 내부에서 호출
    return {"id": doc_id, "updated_doc": updated_doc}


# NmXgO5kBGZxLOL-7152w