package com.example.jobsservice.dto.response;

public class ResponseError<T> extends BaseResponse<T> {
    public ResponseError(String message) {
        super(1, message, null);
    }

    public ResponseError(String message, T data) {
        super(1, message, data);
    }
}