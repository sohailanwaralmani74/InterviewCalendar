package com.calender.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.calender.dtos.UserDto;

/**
 * <h2> RolesValidator
 * RolesValidator for {@link Role}
 * RolesValidator will accept INTERVIEWER or CANDIDATE only
 * 
 * This functionality  can be achieved via ENUMS but implementing custom annotation
 * is just get Idea for custom validation usage. 
 * 
 * Used in {@link UserDto}
 * */

public class RolesValidator implements ConstraintValidator<Role,String>{

	 @Override
	    public void initialize(Role role) {
	    }
	@Override
	public boolean isValid(String role, ConstraintValidatorContext context) {
		
		return role.equals("INTERVIEWER") || role.equals("CANDIDATE") ?true:false;
	}
}
