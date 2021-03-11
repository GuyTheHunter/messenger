from typing import List
from flask import Blueprint, Response, request, make_response, jsonify, g

from server.database import UserDB
from server.config import UserRequestFields, ResponseParameter
from server.exceptions import UserNotFound, UserInvalidArguments, UserNameAlreadyTaken

user_blueprint = Blueprint('server user actions api', __name__, url_prefix='/user')


@user_blueprint.before_request
def pre_handle_request():
    """
    Adds the user's name & password, After extract from the request
    """
    request_body = request.get_json()

    username, password = __extract_data_from_request(request_body, UserRequestFields)

    g.username = username
    g.password = password


@user_blueprint.route('/login', methods=['POST'])
def login() -> Response:
    """
    User login endpoint

    Returns:
        Response: 200 ok with user Token | 400 invalid user request with error message
    """
    username, password = g.username, g.password

    try:
        user_token = UserDB.login(username, password)
        response_body = {ResponseParameter.TOKEN: user_token}
        status_code = ResponseParameter.HTTP_OK

    except (UserNotFound, UserInvalidArguments) as login_error:
        response_body = {ResponseParameter.ERROR: str(login_error)}
        status_code = ResponseParameter.HTTP_USER_INVALID

    return make_response(jsonify(response_body), status_code)


@user_blueprint.route('/register', methods=['POST'])
def register() -> Response:
    """
    User register endpoint

    Returns:
        Response: 200 ok with user token | 400 invalid user request with error message
    """
    username, password = g.username, g.password

    try:
        user_token = UserDB.register(username, password)
        response_body = {ResponseParameter.TOKEN: user_token}
        status_code = ResponseParameter.HTTP_OK

    except (UserNameAlreadyTaken, UserInvalidArguments) as register_error:
        response_body = {ResponseParameter.ERROR: str(register_error)}
        status_code = ResponseParameter.HTTP_USER_INVALID

    return make_response(jsonify(response_body), status_code)


def __extract_data_from_request(request_body: dict, fields_to_extract: List[str]) -> List[str]:
    """
    Extracts the given fields from the request

    Args:
        request_body (dict): User's http request body
        fields_to_extract (list[str]): Fields to extract from the request

    Returns:
        list[str]: The required fields

    Raises:
        ValueError: When one or more fields is not presented
    """
    fields = []

    for field in fields_to_extract:
        if field not in request_body:
            raise ValueError(f'{field} is not present')

        fields.append(request_body[field])

    return fields
