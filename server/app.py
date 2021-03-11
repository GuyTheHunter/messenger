from flask import Flask

from server.config import FlaskParameter
from server.user_blueprint import user_blueprint


def main():
    """
    This is the main function
    """

    app = Flask(__name__)
    app.register_blueprint(user_blueprint)

    app.run(FlaskParameter.HOST, FlaskParameter.PORT)


if __name__ == '__main__':
    main()
