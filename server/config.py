class FlaskParameter:
    HOST = 'localhost'
    PORT = 8080


class DataBaseCluster:
    HOST = 'localhost'
    PORT = 27017

    DB = 'messenger_db'
    USER_DB = 'users'
    MESSAGES_DB = 'messages'


class DataBaseUserParameters:
    ID = '_id'
    PROFILE = 'profile'
    USERNAME = 'username'
    PASSWORD = 'password'


class ResponseParameter:
    TOKEN = 'token'
    ERROR = 'error'

    HTTP_OK = 200
    HTTP_USER_INVALID = 400


UserRequestFields = ['username', 'password']
