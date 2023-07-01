package com.paymentchain.customer.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.paymentchain.customer.common.StandarizedApiExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleNotContentException(NoSuchElementException ex){
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion","Error-SPMC0001",ex.getMessage());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBusinessRuleException(BusinessRuleException ex){
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion","Error-SPMC0001",ex.getMessage());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleNoSuchElementException(WebClientResponseException ex){
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion","Error-SPMC0001",ex.getMessage());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
    }

}
