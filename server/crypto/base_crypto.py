from abc import ABCMeta, abstractmethod


class BaseCrypto(metaclass=ABCMeta):

    @abstractmethod
    def encode(self, *args) -> str:
        """
        Encode the given args

        Args:
            *args list[any]: Data to encode

        Returns:
            str: Encoded data
        """
        pass
