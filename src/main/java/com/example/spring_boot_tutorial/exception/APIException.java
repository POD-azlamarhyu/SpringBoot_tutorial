package com.example.spring_boot_tutorial.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;

    public APIException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatusCode(){
        return httpStatus;
    }

    public void setStatusCode(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}

