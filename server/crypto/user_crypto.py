from .base_crypto import BaseCrypto


class UserCrypto(BaseCrypto):
    def __init__(self, hasher_cls):
        self._hasher_cls = hasher_cls

    def __init_hahser(self):
        """
        Initials the hasher object
        """
        self._hasher = self._hasher_cls()

    def encode(self, username: str, password: str) -> str:
        """
        Encode the user's password

        Args:
            username (str): User's name
            password (str): User's password

        Returns:
            str: Encoded user's password
        """
        self.__init_hahser()

        salted_password = self._salt(username, password)
        self._hasher.update(salted_password.encode('utf8'))
        return self._hasher.hexdigest()

    @staticmethod
    def _salt(username: str, password: str) -> str:
        """
       Salt the user's password

        Args:
            username (str): User's name
            password (str): User's password

        Returns:
            str: Salted user's password
        """
        return ''.join([username + letter for letter in password])
