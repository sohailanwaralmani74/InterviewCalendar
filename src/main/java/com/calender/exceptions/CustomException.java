package com.calender.exceptions;

import lombok.Data;

@Data
public class CustomException {
	
	private String code;
	private String message;
	private String reason;

}
