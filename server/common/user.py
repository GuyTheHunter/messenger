from server.database import UserDB
from server.exceptions import UserNotFound


class User:
    def __init__(self, _id: str, username: str, profile: str = None):
        """
        Initials user instance

        Args:
            _id (str): User's id
            username (str): User's name
            profile (str | None): User's profile as base64
        """
        self._id = _id
        self._username = username
        self._profile = profile

    @property
    def token(self) -> str:
        """
        Getter for User's id (Token)

        Returns:
            str: User's id
        """
        return self._id

    @property
    def username(self) -> str:
        """
        Getter fot User's name

        Returns:
            str: User's name
        """
        return self._username

    @property
    def profile(self) -> str or None:
        """
        Getter for User's profile

        Returns:
            str | None = User's profile
        """
        return self._profile

    @classmethod
    def get_user_by_id(cls, user_id: str) -> User or None:
        """
        Finds the respective user by his id

        Args:
            user_id (str): User's id

        Returns:
            User | None: The respective user
        """
        try:
            _id, username, profile = UserDB.get_user_data_by_id(user_id)
            return cls(_id, username, profile)

        except UserNotFound:
            return None
