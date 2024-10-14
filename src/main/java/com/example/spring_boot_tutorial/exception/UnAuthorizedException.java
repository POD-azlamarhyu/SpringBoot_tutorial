package com.example.spring_boot_tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    private String message;
    private String apiName;

    public UnAuthorizedException(String apiName,String message){
        super(String.format("%s is not allowed.",apiName));
        this.message = message;
        this.apiName = apiName;
    }

    public String getMessage(){
        return message;
    }

    public String getApiName(){
        return apiName;
    }
}
