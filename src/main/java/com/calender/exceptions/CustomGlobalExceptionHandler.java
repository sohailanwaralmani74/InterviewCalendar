package com.calender.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomGlobalExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleExceptions( BusinessException exception, WebRequest webRequest) {
		CustomException response = new CustomException();
        response.setCode(exception.getCode());
        response.setMessage(exception.getMessage());
        response.setReason(exception.getFailureReason());
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
	
}
