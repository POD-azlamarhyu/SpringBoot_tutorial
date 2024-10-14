package com.example.spring_boot_tutorial.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesNotExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private UUID userId;

    public UserDoesNotExistsException(UUID userId){
        super(String.format("' %s ' is not exists.",userId));
        this.userId=userId;
    }

    public UUID getUuid(){
        return userId;
    }
}
