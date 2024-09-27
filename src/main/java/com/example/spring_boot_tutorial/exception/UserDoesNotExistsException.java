package com.example.spring_boot_tutorial.exception;

import java.util.UUID;

public class UserDoesNotExistsException extends RuntimeException {
    private UUID userId;


    public UserDoesNotExistsException(UUID userId){
        super(String.format("' %s ' is not exists.",userId));
        this.userId=userId;
    }

    public UUID getUuid(){
        return userId;
    }
}
