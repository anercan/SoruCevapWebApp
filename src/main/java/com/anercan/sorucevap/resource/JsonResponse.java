package com.anercan.sorucevap.resource;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JsonResponse<T> {

    HttpStatus httpStatus = HttpStatus.OK;
    String message;
    T value;

    public JsonResponse() {
    }

    public JsonResponse(T value) {
        if (value == null) {
            this.httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            this.value = value;
        }
    }

    public JsonResponse(T value, HttpStatus code) {
        this.value = value;
        this.httpStatus = code;
    }

    public JsonResponse(T value, HttpStatus code, String message) {
        this.value = value;
        this.httpStatus = code;
        this.message = message;
    }

    public JsonResponse(T value, String message) {
        this.value = value;
        this.message = message;
    }
}
