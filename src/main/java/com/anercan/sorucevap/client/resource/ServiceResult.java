package com.anercan.sorucevap.client.resource;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceResult<T> {

    HttpStatus status = HttpStatus.OK;
    T value;
    String message;
    String redirect;

    public ServiceResult() {
    }

    public ServiceResult(T value) {
        if (value == null) {
            this.status = HttpStatus.BAD_REQUEST;
        } else {
            this.value = value;
        }
    }

    public ServiceResult(T value, String message) {
        if (value == null) {
            this.status = HttpStatus.BAD_REQUEST;
        } else {
            this.value = value;
            this.message = message;
        }
    }

    public ServiceResult(T value, HttpStatus code) {
        this.value = value;
        this.status = code;
    }

    public ServiceResult(T value, String message, HttpStatus code) {
        this.value = value;
        this.status = code;
        this.message = message;
    }

    public ServiceResult(T value, String message, String redirect, HttpStatus code) {
        this.value = value;
        this.status = code;
        this.redirect = redirect;
        this.message = message;
    }

    public ServiceResult(HttpStatus code) {
        this.status = code;
    }


    public Boolean isSuccess() {
        return status.is1xxInformational() || status.is2xxSuccessful();
    }

}
