package com.calender.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RolesValidator implements ConstraintValidator<Role,String>{

	 @Override
	    public void initialize(Role role) {
	    }
	@Override
	public boolean isValid(String role, ConstraintValidatorContext context) {
		
		return role.equals("INTERVIEWER") || role.equals("CANDIDATE") ?true:false;
	}
}
