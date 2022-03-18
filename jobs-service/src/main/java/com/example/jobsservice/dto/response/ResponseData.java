package com.example.jobsservice.dto.response;

public class ResponseData<T> extends BaseResponse<T> {
    public ResponseData(T data) {
        super(0, null, data);
    }
}
