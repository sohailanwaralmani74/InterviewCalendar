package com.calender.exceptions;

public class CustomException {

	private String code;
	private String message;
	private String reason;

	public CustomException() {
		super();
	}

	public CustomException(String code, String message, String reason) {
		super();
		this.code = code;
		this.message = message;
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
