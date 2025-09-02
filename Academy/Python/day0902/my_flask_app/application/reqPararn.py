from flask import Flask,request, render_template
import os

# flask : python WAS 라이브러리
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
TEMPLATE_DIR = os.path.join(BASE_DIR, 'templates')

app = Flask(__name__, template_folder=TEMPLATE_DIR)


@app.route('/')
def index():
    # html= "<html><head><meta charset='utf-8'></head><body>"
    # html+="<a href=\'calculate.do?x=10&y=5\'>x=10, y=5</a>"
    # html+="</body></html>"
    # return html

    return render_template('calcInput.html')

@app.get('/calculate.do')
def calculate_do():
    x=int(request.args.get('x')) # request.args.get("reqParam변수명") -> str
    y=int(request.args.get('y'))
    html = "<html><head><meta charset='utf-8'></head><body>"
    html += "<table border='1'>"
    html +="<tr><td>%d + %d = %d</td></tr>"%(x,y,x+y)
    html +="<tr><td>%d - %d = %d</td></tr>"%(x,y,x-y)
    html +="<tr><td>%d x %d = %d</td></tr>"%(x,y,x*y)
    html +="<tr><td>%d / %d = %d</td></tr>"%(x,y,x/y)
    html+="</table>"
    html+="</body></html>"
    return html

@app.post('/gugudan.show')
def gugudan_show():
    s=int(request.form['start'])
    e=int(request.form['end'])
    
    return render_template('gugudan.html',start=s, end=e)



if __name__=='__main__':
    app.run(debug=True )
    