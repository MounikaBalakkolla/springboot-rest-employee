package com.springbootrestemp.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @Value
    (value = "${data.exception.message1}")
    private String message1;
    @Value
    (value = "${data.exception.message2}")
    private String message2;
    @Value
    (value = "${data.exception.message3}")
    private String message3;
    
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity blogNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
        return new ResponseEntity(message2, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    public ResponseEntity handleBlogAlreadyExistsException(EmployeeAlreadyExistsException employeeAlreadyExistsException) {
        return new ResponseEntity(message1, HttpStatus.CONFLICT);
    }
   @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
