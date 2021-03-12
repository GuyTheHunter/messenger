import hashlib
from .user_crypto import UserCrypto as userCrypto

UserCrypto = userCrypto(hashlib.sha256)
