package com.calender.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler
		extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResponseDTO> handleBusinessExceptions(
			BusinessException exception, WebRequest webRequest) {
		ExceptionResponseDTO response = ExceptionResponseDTO.builder()
				.code(exception.getCode()).message(exception.getMessage())
				.build();
		ResponseEntity<ExceptionResponseDTO> entity = new ResponseEntity<>(
				response, exception.getHttpStatus());
		return entity;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		ExceptionResponseDTO responseDTO = ExceptionResponseDTO.builder()
				.code("BAD_REQUEST")
				.message(ex.getMessage())
				.errors(errors).build();
		
		return new ResponseEntity<Object>(responseDTO, HttpStatus.BAD_REQUEST);
	}
}
