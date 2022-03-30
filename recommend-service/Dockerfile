FROM python:3
WORKDIR /app 
COPY requirements.txt requirements.txt
COPY . .
RUN pip3 install -r requirements.txt 


CMD ["python", "app.py"]