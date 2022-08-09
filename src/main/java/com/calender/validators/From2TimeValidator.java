package com.calender.validators;

import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.calender.dtos.UserTimeSlotDto;

/**
 * <h3> From2TimeValidator
 * 
 * From2TimeValidator is validator for {@link From2Time}
 * 
 * validator will check if user input time meets perfect hour only criteria.
 * if from time and to time have perfect hour (no minutes and seconds) it will allow input otherwise
 * will reject
 * @see {@link From2Time} for custom messages.
 * for custom message 
 * Used in {@link UserTimeSlotDto}
 * */

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
