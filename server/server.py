from flask import Flask,  make_response, request
import textwrap
import google.generativeai as genai

def gemini(prompt):

    def to_markdown(text):
        text = text.replace('•', '  *') 
        return textwrap.indent(text, '', predicate=lambda _: True)

    api_key = "AIzaSyC6TNo0LqCSmtmviQb-XVtkpFxHJREemvk"

    if not api_key:
        raise ValueError("Please set the GOOGLE_API_KEY environment variable.")

    genai.configure(api_key=api_key)


    model_name = "gemini-pro"  
    model = genai.GenerativeModel(model_name)


    #prompt = input("promtp: ")  
    response = model.generate_content(prompt)


    return to_markdown(response.text)

app = Flask(__name__)

dati = """
    [
       ok 2000200202020202
    ]
"""

@app.route("/")
def index():
    response = app.response_class(
        response=dati,
        mimetype='application/json'
    )
    response.headers.add("Access-Control-Allow-Origin", "*")
    return response


@app.route("/chatgpt")
def add():
    response = app.response_class(
    response=0,
    mimetype='application/json'
    )
    # recupero i dati
    get_prompt = request.args.get("prompt")
    #get_valore = request.args.get("valore")

    response = gemini(get_prompt)
   
    #response.headers.add("Access-Control-Allow-Origin", "*")
    return response 