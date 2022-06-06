package com.example.common.Response;

public class ResponseData<T> extends BaseResponse<T> {
    public ResponseData(T data) {
        super(0, "test", data);
    }
}
