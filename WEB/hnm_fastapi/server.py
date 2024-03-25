import pandas as pd
from sqlalchemy import create_engine
import mysql.connector

from fastapi import FastAPI

from fastapi.middleware.cors import CORSMiddleware

from model.service import service_model
from database.mysql import insert

app = FastAPI()

origins = [
    "http://localhost",
    "http://localhost:8080",
    "http://localhost:8000"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins = origins,
    allow_credentials = True,
    allow_methods=["*"],
    allow_headers=["*"]
)
# 데이터 가져오기
# MySQL connection setup
engine = create_engine('mysql+mysqlconnector://urstory:u1234@localhost:3306/examplesdb')
# SQL 쿼리 실행하여 데이터 가져오기
articles_table = "SELECT * FROM articles_table" 
# customer_table
articles = pd.read_sql(articles_table, engine)
engine.dispose()
####
engine = create_engine('mysql+mysqlconnector://urstory:u1234@localhost:3306/examplesdb')
# SQL 쿼리 실행하여 데이터 가져오기
transactions_train_table = "SELECT * FROM transactions_train_table" 
transactions = pd.read_sql(transactions_train_table, engine)

@app.get("/prediction")
def prediction():
    prediction = service_model()
    insert(prediction)
    return {"prediction": prediction}

@app.get("/users/me")
async def read_user_me():
    return {"username": "the current user"}

@app.get("/users/{username}")
async def read_user(username: str):
    prediction = service_model(articles,transactions,username)
    return prediction



