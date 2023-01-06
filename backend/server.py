from flask import Flask
from flask import request
from flask import redirect
from flask import url_for
from flask import session
from flask import jsonify


app = Flask(__name__)

# Set the secret key to some random bytes. Keep this really secret!
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'

# Cached database
users = [{ "id": 1, "username": "admin", "password": "123" }]
categories = [{ "id": 1, "name": "c1", "color": "rouge" }]
minerals = [{ "id": 1, "name": "m1", "country": "France", "category": 1 }]

# Session managing
def login(username):
    session['username'] = username

def logout():
    session.pop('username', None)

def is_logged():
    return 'username' in session


# Return message
def json_message(obj, code):
    return jsonify(obj), code

def error_message(message, code):
    return { 'error' : message }, code

def error_not_logged():
    return error_message('not logged', 401)

def error_wrong_credentials():
    return error_message('Could not connect with provided crendentials', 401)


# Find in cached database
find = lambda fun, lst: next((x for x in lst if fun(x)), None)
find_all = lambda fun, lst: [x for x in lst if fun(x)]

def check_login(username, password):
    return find(lambda x: x.get('username') == username and x.get('password') == password, select_all_users()) is not None

def find_user_by_id(user_id):
    return find(lambda x: x.get('id') == user_id, select_all_users())

def find_mineral_by_id(mineral_id):
    return find(lambda x: x.get('id') == mineral_id, select_all_minerals())

def find_all_minerals_by_category_id(category_id):
    return find_all(lambda x: x.get('category') == category_id, select_all_minerals())

def find_category_by_id(category_id):
    return find(lambda x: x.get('id') == category_id, select_all_categories())


# Commande BDD
def select_all_users():
    return users

def select_all_categories():
    return categories

def insert_category(category):
    categories.append(category)

def update_category(category):
    pass

def remove_category(category):
    list_minerals = find_all_minerals_by_category_id(category.get('id'))
    for m in list_minerals:
        remove_mineral(m)
    categories.remove(category)

def select_all_minerals():
    return minerals

def insert_mineral(mineral):
    minerals.append(mineral)

def update_mineral(mineral):
    pass

def remove_mineral(mineral):
    minerals.remove(mineral)

# Endpoints
def post_login(form):
    if check_login(form.get('username'), form.get('password')):
        login(form.get('username'))
        return 'Session is ready. Go to /logout to kill the session.', 200
    else:
        return error_wrong_credentials()

def get_user_id(user_id):
    user = find_user_by_id(user_id)
    return json_message(user, 200)

def get_mineral():
    return json_message(select_all_minerals(), 200)

def put_mineral(form):
    name = form.get('name')
    country = form.get('country')
    category = form.get('category')

    if name is None:
        return error_message('Name is mendatory.', 401)
    if name == '':
        return error_message('Name must not be empty', 401)
    if category is None:
        return error_message('Category is mendatory.', 401)
    if find_category_by_id(int(category)) is None:
        return error_message('Category must exist.', 401)

    new_id = -1
    for m in select_all_minerals():
        if m.get('id') > new_id:
            new_id = m.get('id')
    new_id = new_id + 1

    insert_mineral({ 'id': new_id, 'name': name, 'country': country, 'category': int(category) })
    return 'Ok', 200

def get_mineral_id(mineral_id):
    return json_message(find_mineral_by_id(mineral_id), 200)

def post_mineral_id(mineral_id, form):
    mineral = find_mineral_by_id(mineral_id)
    
    name = form.get('name')
    country = form.get('country')
    category = form.get('category')

    if mineral is None:
        return error_message('No mineral found.', 401)
    if name is not None:
        if name == '':
            return error_message('Name must not be empty', 401)
        mineral['name'] = name
    if country is not None:
        mineral['country'] = country
    if category is not None:
        if find_category_by_id(int(category)) is None:
            return error_message('Category must exist.', 401)
        mineral['category'] = int(country)

    update_mineral(mineral)
    return 'Ok', 200

def delete_mineral_id(mineral_id):
    mineral = find_mineral_by_id(mineral_id)
    if mineral is None:
        return error_message('No mineral found.', 401)
    remove_mineral(mineral)
    return 'Ok', 200

def get_category():
    return json_message(select_all_categories(), 200)

def put_category(form):
    print(form)
    name = form.get('name')
    color = form.get('color')

    if name is None:
        return error_message('Name is mendatory.', 401)
    if name == '':
        return error_message('Name must not be empty', 401)
    if color is None:
        return error_message('Color is mendatory.', 401)
    if color == '':
        return error_message('Color must not be empty', 401)
 
    new_id = -1
    for m in select_all_categories():
        if m.get('id') > new_id:
            new_id = m.get('id')
    new_id = new_id + 1

    insert_category({ 'id': new_id, 'name': name, 'color': color })
    return 'Ok', 200

def get_category_id(category_id):
    return json_message(find_all_minerals_by_category_id(category_id), 200)

def post_category_id(category_id, form):
    category = find_category_by_id(category_id)
    name = form.get('name')
    color = form.get('color')

    if category is None:
        return error_message('No category found.', 400)
    if name is not None:
        if name == '':
            return error_message('Name must not be empty', 400)
        category['name'] = name
    if color is not None:
        if color == '':
            return error_message('Color must not be empty', 400)
        category['color'] = color

    update_category(category)
    return 'Ok', 200


def delete_category_id(category_id):
    category = find_category_by_id(category_id)
    if category is None:
        return error_message('No category found.', 401)
    remove_category(category)
    return 'Ok', 200


# Endpoints of the API

@app.route('/login', methods=['GET', 'POST'])
def login_route():
    if request.method == 'GET':
        return post_login({ 'username': 'admin', 'password': '123' })
    if request.method == 'POST':
        return post_login(request.json)

@app.route('/logout')
def logout_route():
    logout()
    return 'You are disconnected'

@app.route('/user/<int:user_id>', methods=['GET'])
def user_id_route(user_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return get_user_id(user_id)

@app.route('/mineral', methods=['GET', 'PUT'])
def mineral_route():
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return get_mineral()
    elif request.method == 'PUT':
        return put_mineral(request.json)
        

@app.route('/mineral/<int:mineral_id>', methods=['GET', 'POST', 'DELETE'])
def mineral_id_route(mineral_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return get_mineral_id(mineral_id)
    elif request.method == 'POST':
        return post_mineral_id(mineral_id, request.json)
    elif request.method == 'DELETE':
        return delete_mineral_id(mineral_id)

@app.route('/category', methods=['GET', 'PUT'])
def category_route():
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return get_category()
    elif request.method == 'PUT':
        return put_category(request.json)

@app.route('/category/<int:category_id>', methods=['GET', 'POST', 'DELETE'])
def category_id_route(category_id):
    if not is_logged():
        return error_not_logged()
    if request.method == 'GET':
        return get_category_id(category_id)
    elif request.method == 'POST':
        return post_category_id(category_id, request.json)
    elif request.method == 'DELETE':
        return delete_category_id(category_id)

