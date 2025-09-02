from flask import Flask,render_template,redirect,request,url_for,jsonify
# import os

app=Flask(__name__)

memo={"id":None,"title":None,"content":None}

note=[]

@app.route('/')
def index():
    return render_template('memo/index.html',note=note)

@app.route('/add',methods=["POST","GET"])
def add():
    if request.method=="POST":
        new_memo=memo.copy()
        new_memo['id']=len(note)+1
        new_title=request.form['title']
        new_content=request.form['content']
        new_memo['title']=new_title
        new_memo['content']=new_content

        note.append(new_memo)
        return redirect(url_for('index'))
    
    return render_template("add.html")

@app.route("/edit/<int:memo_id>",methods=["POST","GET"])
def edit(memo_id):
  # GET/POST 공통: memo_id에 해당하는 메모 찾기
    memo_to_edit = None
    for i in note:
        if i['id'] == memo_id:
            memo_to_edit = i
            break

    if memo_to_edit is None:
        return "메모를 찾을 수 없습니다.", 404

    if request.method == "POST":
        memo_to_edit['title'] = request.form['title']
        memo_to_edit['content'] = request.form['content']
        return redirect(url_for('index'))

    # GET: 수정 폼에 기존 내용을 채워서 렌더
    return render_template("update.html", memo=memo_to_edit)

@app.route("/delete/<int:memo_id>",methods=["POST","GET"])
def delete(memo_id):
    for k,v in enumerate(note) :
        if v['id']==memo_id:
            note.pop(k)
            break
    return redirect(url_for('index'))


if __name__=="__main__":
    app.run(debug=True)
