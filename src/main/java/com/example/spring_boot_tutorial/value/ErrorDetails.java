package com.example.spring_boot_tutorial.value;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private Integer httpStatusCode;


    public ErrorDetails(Integer httpStatusCode, String message , LocalDateTime timestamp) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getHttpStatusCode(){
        return httpStatusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}
