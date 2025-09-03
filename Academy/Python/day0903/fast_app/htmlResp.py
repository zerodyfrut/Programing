from fastapi import FastAPI
from fastapi.responses import HTMLResponse

app=FastAPI()

@app.get('/html.test')
def html_test():
    html="<html><head><meta chartset=\"utf-8\"></head><body>"
    html+= "<marquee>fast api 공부중!! </marquee>"
    html+="</body></html>"
    
    return HTMLResponse(html)