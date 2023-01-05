from flask import Flask
from flask import request
from flask import redirect
from flask import url_for
from flask import session
from flask import jsonify


app = Flask(__name__)

# Set the secret key to some random bytes. Keep this really secret!
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'

users = [{ "id": 1, "user": "admin", "password": "123" }]
categories = [{ "id": 1, "name": "c1", "color": "rouge" }]
minerals = [{ "id": 1, "name": "m1", "country": "France", "category": 1 }]

find = lambda fun, lst: next((x for x in lst if fun(x)), None)
find_all = lambda fun, lst: [x for x in lst if fun(x)]

def login(username):
    session['username'] = username

def logout():
    session.pop('username', None)

def is_logged():
    return 'username' in session

def error_message(message, code):
    return { 'error' : message }, code

def error_not_logged():
    return error_message('not logged', 401)

def error_wrong_credentials():
    return error_message('Could not connect with provided crendentials', 401)

def check_login(username, password):
    return find(lambda x: x.username == username and x.password == password, users) is not None

def get_all_users():
    return users

def get_user_by_id(user_id):
    return find(lambda x: x['id'] == user_id, get_all_users())

def get_all_categories():
    return categories

def get_all_minerals():
    return minerals

def get_mineral_by_id(mineral_id):
    return find(lambda x: x['id'] == mineral_id, get_all_minerals())

def get_minerals_by_category_id(category_id):
    return find_all(lambda x: x['category'] == category_id, get_all_minerals())


@app.route('/login', methods=['GET', 'POST'])
def login_route():
    if request.method == 'GET':
        login('admin')
        return 'Session is ready. Go to /logout to kill the session.'
    if request.method == 'POST':
        if check_login(request.form['username'], request.form['password']):
            login(request.form['username'])
            return 'Session is ready. Go to /logout to kill the session.'
        else:
            return error_wrong_credentials()

@app.route('/logout')
def logout_route():
    logout()
    return 'You are disconnected'

@app.route('/user/<int:user_id>', methods=['GET'])
def user_id_route(user_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        user = get_user_by_id(user_id)
        return jsonify(user), 200

@app.route('/mineral', methods=['GET', 'PUT'])
def mineral_route():
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return jsonify(get_all_minerals()), 200
    elif request.method == 'PUT':
        return

@app.route('/mineral/<int:mineral_id>', methods=['GET', 'POST', 'DELETE'])
def mineral_id_route(mineral_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return jsonify(get_mineral_by_id(mineral_id)), 200
    elif request.method == 'POST':
        return
    elif request.method == 'DELETE':
        return

@app.route('/category', methods=['GET', 'PUT'])
def category_route():
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return jsonify(get_all_categories()), 200
    elif request.method == 'PUT':
        return

@app.route('/category/<int:category_id>', methods=['GET', 'POST', 'DELETE'])
def category_id_route(category_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return jsonify(get_minerals_by_category_id(category_id)), 200
    elif request.method == 'POST':
        return
    elif request.method == 'DELETE':
        return

