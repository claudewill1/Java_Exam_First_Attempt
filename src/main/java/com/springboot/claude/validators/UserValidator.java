package com.springboot.claude.validators;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator {
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}
	
	public void validateUser(Object target, Errors errors) {
		User user = (User)target;
		
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match Error","Passwords do not match!");
		}
	}
}
