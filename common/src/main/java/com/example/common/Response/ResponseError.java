package com.example.common.Response;

public class ResponseError<T> extends BaseResponse<T> {
    public ResponseError(String message) {
        super(2, message, null);
    }

    public ResponseError(String message, T data) {
        super(2, message, data);
    }
}