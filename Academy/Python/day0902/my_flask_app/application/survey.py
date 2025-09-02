from flask import Flask, jsonify,render_template, request,redirect,url_for

app= Flask(__name__)
app.secret_key='secret123'

# 설문 데이터
survey={
    "question":"가장 좋아하는 과일은?",
    "choices":["사과","바나나","포도","멜론"],
    "votes": [0,0,0,0]
}

@app.route("/",methods=["GET","POST"])
def index():
    if request.method=="POST":

        choice_index=int(request.form["choice"])
        # print(choice_index)
        survey["votes"][choice_index]+=1

        return redirect(url_for("result"))
    return render_template("survey/index.html",survey=survey)

@app.route("/result")
def result():
    return render_template("survey/result.html",survey=survey)

#Ajax용 엔드 포인트: 투표 데이터 변환
@app.route("/votes")
def votes():
    total =sum(survey["votes"])
    percentages=[round(v/total*100,1) if total>0 else 0 for v in survey["votes"]]
    return jsonify({"votes":survey["votes"],"percentages":percentages})

if __name__=="__main__":
    app.run(debug=True)