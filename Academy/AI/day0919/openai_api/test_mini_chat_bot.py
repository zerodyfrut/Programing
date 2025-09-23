#pip install openai
from openai import OpenAI
import os

api_key = "***REMOVED***proj-O4eZwgibPIJNZnIz0Ev7O2FAI2FDZorq99PQyIrhSsz_A0zkxjqdi-Mk_8fV_NXWY3aXRQkuSHT3BlbkFJBDT-sJc3yqfvD56Q1o2_LpHdghC8sJmNgvguqeueq5zhygqNUP996ntIAVJWvbK2VN8gP3WdIA"

# API 키 설정 (환경 변수 사용 권장)
client = OpenAI(api_key=api_key)

def chat_with_gpt(messages):
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=messages,
        temperature=0.7  # 창의성 조절 (0 ~ 2) : 클수록 창의적이라는데..
    )
    return response.choices[0].message.content

def main():
    print("=== 미니 챗봇 시작 ===")
    messages = [{"role": "system", "content": "You are a helpful assistant."}]

    while True:
        user_input = input("You: ")
        if user_input.lower() in ["exit", "quit"]:
            print("챗봇 종료!")
            break

        messages.append({"role": "user", "content": user_input})
        bot_reply = chat_with_gpt(messages)
        messages.append({"role": "assistant", "content": bot_reply})
        # 메세지(기록)를 누적하여 기존 대화를 유지

        print("Bot:", bot_reply)


if __name__ == "__main__":
    main()
