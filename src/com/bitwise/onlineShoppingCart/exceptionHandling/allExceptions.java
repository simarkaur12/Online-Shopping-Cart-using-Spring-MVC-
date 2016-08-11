package com.bitwise.onlineShoppingCart.exceptionHandling;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class allExceptions extends Throwable{
	private static final long serialVersionUID = 1L;
	@ExceptionHandler(value= Throwable.class)
	public ModelAndView ProductItemOutOfStock(ModelMap model){
		model.addAttribute("error","Poduct Item Is out of stock");
		return new ModelAndView("globalErrorPage",model);
	}
}
