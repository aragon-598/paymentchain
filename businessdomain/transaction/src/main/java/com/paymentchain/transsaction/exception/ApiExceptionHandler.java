package com.paymentchain.transsaction.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paymentchain.transsaction.common.StandarizedApiExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleNotContentException(BusinessRuleException ex){
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion",ex.getCode(),ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }
    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<StandarizedApiExceptionResponse> handleNotContentException(Exception ex){
//        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion","Error-SPMC0001",ex.getMessage());
//        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
//    }

}
