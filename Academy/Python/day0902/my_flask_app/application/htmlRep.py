from flask import Flask

# flask : python WAS 라이브러리

app=Flask(__name__) # 현재 파일을 기준으로 내부 경로를 잡을 수 있음

# GET : html.text 요청
@app.get('/html.test')
def html_test():
    html = "<html><head><meta charset=\"utf-8\"></head><body>"
    html += "<marquee>플라스크 웹 개발중</marquee>"
    html += "</body></html>"
    return html

# GET :xy.calculate 요청
@app.get('/xy.calculate')
def xy_calculate():
    a = 10
    b = 20
    c = a + b
    html = "<html><head><meta charset=\"utf-8\"></head><body>"
    html += "<h1>%d</h1>" % c
    html += "</body></html>"
    return html

# GET : gugudan.show
@app.get('/gugudan.show')
def gugudan_show():
    html = "<html><head><meta charset=\"utf-8\"></head><body>"
    for dan in range(2, 10):
        html += "<table border=\"1\" style=\"float:left;\">"
        html += "<tr><th>%d단</th></tr>" % dan
        for i in range(1, 10):
            html += "<tr><td>%d x %d = %d</td></tr>" % (dan, i, dan*i)
        html += "</table>"
    html += "</body></html>"
    return html

if __name__=='__main__':
    app.run(debug=True )