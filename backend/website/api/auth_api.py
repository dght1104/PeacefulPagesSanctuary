from flask import Blueprint, jsonify

api_auth = Blueprint('api_auth', __name__)

@api_auth.route('/ping', methods=['GET'])
def ping():
    return jsonify({
        "status": "ok",
        "message": "Flask API is working"
    })