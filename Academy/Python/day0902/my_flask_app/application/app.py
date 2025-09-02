from flask import Flask,render_template,request,redirect, url_for
# import os

# BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# TEMPLATE_DIR = os.path.join(BASE_DIR, 'templates')

# app = Flask(__name__, template_folder=TEMPLATE_DIR)
app = Flask(__name__)
# 임시 데이터
users={'solde':'1234'}
posts=[
    {"title":"첫 번째 글","author":"solde"},
    {"title":"두 번째 글","author":"guest"}]

current_user=None # 로그인 여부 저장, 실제 서비스라면 세션/DB 사용

@app.route("/")
def home():
    # current_user=session.get("user")
    return render_template("login/index.html",user=current_user)

@app.route("/login",methods=["GET","POST"])
def login():
    global current_user
    if request.method=="POST":
        username=request.form["username"]
        password=request.form["password"]
        if username in users and users[username]==password:
            current_user=username
            # session["user"]=username
            return redirect(url_for("home")) # home이라는 함수 이름이 등록된 URL찾아서 문자열로 반환
        else:
            return render_template("login/login.html",error="로그인 실패!")
    return render_template("login/login.html")

@app.route("/logout")
def logout():
    global current_user
    current_user=None
    # session.clear()
    return redirect(url_for("home"))

@app.route("/posts")
def post_list():
    return render_template("login/posts.html",posts=posts,user=current_user)

if __name__=="__main__":
    app.run(debug=True)
