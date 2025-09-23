# pip install chromadb

from openai import OpenAI
import chromadb
api_key = "***REMOVED***proj-O4eZwgibPIJNZnIz0Ev7O2FAI2FDZorq99PQyIrhSsz_A0zkxjqdi-Mk_8fV_NXWY3aXRQkuSHT3BlbkFJBDT-sJc3yqfvD56Q1o2_LpHdghC8sJmNgvguqeueq5zhygqNUP996ntIAVJWvbK2VN8gP3WdIA"

client = OpenAI(api_key=api_key)

# 1) Chroma DB 초기화
chroma_client = chromadb.Client()
collection = chroma_client.create_collection(name="company_docs")


print("1")

# 2) 문서 임베딩 후 DB에 추가
docs = [
    "사내 복지 규정: 연차는 15일에서 시작…",
    "IT 보안 정책: 비밀번호는 12자 이상…",
]
print("2")

for i, d in enumerate(docs):
    emb = client.embeddings.create(model="text-embedding-3-small", input=d).data[0].embedding
    print("3")

    collection.add(ids=[f"doc_{i}"], embeddings=[emb], documents=[d])
    print("4")

# 3) 검색 + GPT 답변
def ask(query):
    q_emb = client.embeddings.create(model="text-embedding-3-small", input=query).data[0].embedding
    print("5")

    results = collection.query(query_embeddings=[q_emb], n_results=2)
    print("6")

    context = "\n".join(results["documents"][0])
    print("7")

    messages = [
        {"role": "system", "content": "You are a helpful assistant for company policies."},
        {"role": "user", "content": f"문서 내용:\n{context}\n\n질문: {query}"}
    ]
    print("8")

    resp = client.chat.completions.create(model="gpt-3.5-turbo", messages=messages)
    print("9")

    return resp.choices[0].message.content

print(ask("연차는 며칠부터 시작해?"))
print("10")
