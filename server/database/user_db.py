from typing import Dict, Tuple
from pymongo.collection import Collection, ObjectId

from server.crypto import UserCrypto
from server.config import DataBaseUserParameters
from server.exceptions import UserNameAlreadyTaken, UserInvalidArguments, UserNotFound


class UserDB:
    def __init__(self, collection: Collection):
        self.collection = collection

    def login(self, username: str, password: str) -> str:
        """
        Returns user's token if registered

        Args:
            username (str): User's name
            password (str): User's password

        Returns:
            str: User's token

        Raises:
            UserNotFound: When user is not found
            UserInvalidArguments: When invalid parameters are given
        """
        if any([len(arg) == 0 for arg in (username, password)]):
            raise UserInvalidArguments('Please provide a valid parameters')

        result = self.collection.find_one(self.__build_document_object(username, password))

        if not result:
            raise UserNotFound('Not such user')

        return str(result[DataBaseUserParameters.ID])

    def register(self, username: str, password: str) -> str:
        """
        Register a user to the db

        Args:
            username (str): User's name
            password (str): User's password

        Returns:
            str: User's token

        Raises:
            UserNameAlreadyTaken: When user's name already
            UserInvalidArguments: When invalid parameters are given
        """
        if any([len(arg) == 0 for arg in (username, password)]):
            raise UserInvalidArguments('Please provide a valid parameters')

        if self.__is_user_name_taken(username):
            raise UserNameAlreadyTaken(f'{username} is already taken')

        result = self.collection.insert_one(self.__build_document_object(username, password))
        return str(result.inserted_id)

    def get_user_data_by_id(self, user_id) -> Tuple[str, str, str or None]:
        """
        Finds the user's information

        Args:
            user_id (str): User's id to search for

        Returns:
            tuple[str, str, bytes or None]
        """
        result = self.collection.find_one({DataBaseUserParameters.ID: ObjectId(user_id)})
        if not result:
            raise UserNotFound(f'User id "{user_id}" was not found')

        username, profile = result[DataBaseUserParameters.USERNAME], result.get(DataBaseUserParameters.PROFILE)
        return user_id, username, profile

    def __is_user_name_taken(self, username: str) -> bool:
        """
        Checks whether the given name is already taken

        Args:
            username (str): User's name to be checked

        Returns:
            bool whether the given user's name already taken
        """
        result = self.collection.find_one({DataBaseUserParameters.USERNAME: username})
        return bool(result)

    @staticmethod
    def __build_document_object(username: str, password: str) -> Dict:
        """
        Creates a user mongodb document object

        Args:
            username (str): User's name
            password (str): User's password

        Returns:
            dict: User mongodb document object
        """
        return {DataBaseUserParameters.USERNAME: username,
                DataBaseUserParameters.PASSWORD: UserCrypto.encode(username, password)}

    def reset_db(self):
        self.collection.delete_many({})
