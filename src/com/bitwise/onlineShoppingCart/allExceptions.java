package com.bitwise.onlineShoppingCart;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class allExceptions extends Throwable{
	private static final long serialVersionUID = 1L;
	@ExceptionHandler(value={ Throwable.class,RuntimeException.class, Exception.class})
	public ModelAndView ProductItemOutOfStock(ModelMap model){
		model.addAttribute("error","Poduct Item Is out of stock");
		return new ModelAndView("globalErrorPage",model);
	}
}
