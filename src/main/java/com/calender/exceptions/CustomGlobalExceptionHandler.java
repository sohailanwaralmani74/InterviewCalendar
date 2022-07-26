package com.calender.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomGlobalExceptionHandler {
	
	@ExceptionHandler(InterviewerNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( InterviewerNotFoundException exception, WebRequest webRequest) {
		CustomException response = new CustomException();
        response.setCode("404");
        response.setMessage("Interviewer Not found");
        response.setReason("Interviewer Provided is available in records");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
	
	@ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( CandidateNotFoundException exception, WebRequest webRequest) {
		CustomException response = new CustomException();
        response.setCode("404");
        response.setMessage("Candidate Not found");
        response.setReason("Candidate Provided is available in records");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
	
	@ExceptionHandler(SlotReservationException.class)
    public ResponseEntity<Object> handleExceptions( SlotReservationException exception, WebRequest webRequest) {
		CustomException response = new CustomException();
        response.setCode("Business Error");
        response.setMessage("invalid Slot");
        response.setReason("Slot you have selected, do not matcheds with candidate slot, time should be same for both");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }

}
