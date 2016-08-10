package com.bitwise.onlineShoppingCart.Models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return CustomerLogin.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		CustomerLogin customer = (CustomerLogin) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uname", "error.uname", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required");
		if(customer.getUname().length()<=2){
			errors.rejectValue("uname", "smallLength", new Object[]{"'uname'"},"Username must have more than 2 characters");
		}
	}
}
