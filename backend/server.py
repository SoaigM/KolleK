from flask import Flask



@app.route('/programming_languages/<programming_language_name>')
def get_programming_language(programming_language_name):
    return in_memory_datastore[programming_language_name]

@app.route('/login', methods=['POST'])
def programming_languages_route():
   if request.method == "POST":
       return login(request)


app = Flask(__name__)

