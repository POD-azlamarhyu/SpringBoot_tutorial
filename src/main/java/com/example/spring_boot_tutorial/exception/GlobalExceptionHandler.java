package com.example.spring_boot_tutorial.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.example.spring_boot_tutorial.value.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorDetails> handleAPIException(APIException apiException,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
            apiException.getStatusCode().value(),
            apiException.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails,apiException.getStatusCode());
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<?> handleUnAuthorizedException(
        UnAuthorizedException unAuthorizedException,
        WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
            HttpStatus.UNAUTHORIZED.value(),
            unAuthorizedException.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
        Exception ex,
        WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserDoesNotExistsException.class})
    public ResponseEntity<ErrorDetails> handleUserDoesNotExistsException(
        UserDoesNotExistsException userDoesNotExistsException,WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
            HttpStatus.NOT_FOUND.value(),
            userDoesNotExistsException.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(
        ResourceNotFoundException resourceNotFoundException,
        WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
            HttpStatus.NOT_FOUND.value(), 
            resourceNotFoundException.getMessage(), 
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

}
