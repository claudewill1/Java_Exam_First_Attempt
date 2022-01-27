package com.springboot.claude.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.springboot.claude.models.User;

@Component
public class UserValidator {
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}
	
	public void validate(Object tar, Errors errors) {
		User user = (User) tar;
		
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Match");
		}
	}

	
}
