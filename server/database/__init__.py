from pymongo import MongoClient

from .user_db import UserDB as userDB
from server.config import DataBaseCluster

cluster = MongoClient(DataBaseCluster.HOST, DataBaseCluster.PORT)
db = cluster[DataBaseCluster.DB]

UserDB = userDB(db[DataBaseCluster.USER_DB])
