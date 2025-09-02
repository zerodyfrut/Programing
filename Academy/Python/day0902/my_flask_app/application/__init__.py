from flask import Flask

from my_flask_app.application import routes

app = Flask(__name__)

from application import app  # routes.py 등록