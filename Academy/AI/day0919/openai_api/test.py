#pip install openai

from openai import OpenAI
api_key = "***REMOVED***proj-O4eZwgibPIJNZnIz0Ev7O2FAI2FDZorq99PQyIrhSsz_A0zkxjqdi-Mk_8fV_NXWY3aXRQkuSHT3BlbkFJBDT-sJc3yqfvD56Q1o2_LpHdghC8sJmNgvguqeueq5zhygqNUP996ntIAVJWvbK2VN8gP3WdIA"
client = OpenAI(api_key=api_key)


response = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": "안녕, 오늘 서울 날씨 어때?"}
    ]

)

print(response.choices[0].message.content)