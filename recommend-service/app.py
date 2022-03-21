from flask import Flask
import json
from flask import request,jsonify
import mariadb
import sys
import py_eureka_client.eureka_client as eureka_client



rest_port = 8050
eureka_client.init(eureka_server="http://localhost:8761/eureka",
                   app_name="recommend-serivce",
                   instance_port=rest_port)

rest_port = 8050
try: 
   conn = mariadb.connect(
      user="root",
      password="1234567890",
      host="127.0.0.1",
      port=3307,
      database="recommend-service"
   )
except mariadb.Error as e:
   print(f"error connect to mariadb:  {e}" )
   sys.exit(1)

cur = conn.cursor()

app = Flask(__name__)
@app.route('/cv', methods=['POST'])
def create():
   try:
      _json = request.json
      id = _json['id']
      name = _json['name']
      url = _json['url']
      active = _json['active']
      cur.execute("INSERT INTO cv (id, name,url,active) VALUES (?,?,?,?)",(id,name,url,active))
   except mariadb.Error as e:
      print(e)
   conn.commit()
   return "Adding CV successfully"

@app.route('/cv',methods=['GET'])
def get():
    try:
        cur.execute("SELECT * FROM cv")
        result = (cur.fetchall())
    except Exception as e:
        print(e)
    rs = []
    result
    for i in result:
        key = ["id","name","url","active"]
        a = dict(zip(key,i))
        rs.append(a)
    return jsonify(results = rs)
@app.route('/hello')
def index():
    return '<h1>Hellos!</h1>'

if __name__ == "__main__":
    app.debug = True
    app.run(host='0.0.0.0', port = rest_port)