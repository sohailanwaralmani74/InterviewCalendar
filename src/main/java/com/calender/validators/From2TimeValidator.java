package com.calender.validators;

import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class From2TimeValidator implements ConstraintValidator<From2Time,String>{

	 @Override
	    public void initialize(From2Time from) {
	    }
	@Override
	public boolean isValid(String from, ConstraintValidatorContext context) {
		LocalTime t = LocalTime.parse(from);
		return t.getMinute() >0 || t.getSecond() > 0 ? false:true;
		
	}
}
