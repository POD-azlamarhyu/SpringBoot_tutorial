package com.example.spring_boot_tutorial.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.spring_boot_tutorial.value.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleAPIException(APIException apiException,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), apiException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
