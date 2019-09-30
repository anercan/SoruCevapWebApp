package com.anercan.sorucevap.entity;

import java.util.Optional;

public class JsonResponse<T> {

    int code=0;
    String message="Success";
    T value;

    public JsonResponse() {
    }

    public JsonResponse(T value) {
        this.value = value;
    }

    public JsonResponse(T value,int code) {
        this.value = value;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
