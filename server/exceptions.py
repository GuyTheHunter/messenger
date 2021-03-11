class BaseMessengerException(Exception):
    pass


class UserInvalidArguments(BaseMessengerException):
    pass


class UserNameAlreadyTaken(UserInvalidArguments):
    pass


class UserNotFound(UserInvalidArguments):
    pass
