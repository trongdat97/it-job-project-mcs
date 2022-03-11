package com.example.apigateway.model;

public class ResponseData<T> {
    private int code = 0;
    private String message = "a";
    T data;

    public ResponseData(T data ){
        this.data = data;
    }

    public ResponseData(int code, String message,T data ){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public T getUser(){
        return data;
    }
    public void setUser(T data){
        this.data = data;
    }
}
