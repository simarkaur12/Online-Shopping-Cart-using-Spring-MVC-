package com.bitwise.onlineShoppingCart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwise.onlineShoppingCart.Models.CustomerLogin;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginpage(@ModelAttribute("CustomerLogin")CustomerLogin customer,ModelMap model) {
		return "/cart/login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logout(ModelMap model) {
		return "/logout/logout";
	}
	
}
