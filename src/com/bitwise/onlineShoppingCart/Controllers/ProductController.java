package com.bitwise.onlineShoppingCart.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwise.onlineShoppingCart.Models.CustomerDetails;
import com.bitwise.onlineShoppingCart.Models.CustomerLogin;
import com.bitwise.onlineShoppingCart.Models.LoginValidator;
import com.bitwise.onlineShoppingCart.Models.Product;
import com.bitwise.onlineShoppingCart.Models.ProductList;

@Controller
public class ProductController {
	
	HashMap<String, String> security;
	private static String uname;
	
	@Autowired
	CustomerLogin customer;
	
	@Autowired
	LoginValidator LoginValidator; 

	@InitBinder("CustomerLogin")
	private void initBinder(WebDataBinder binder){
		binder.setValidator(LoginValidator);
	}
	
	@RequestMapping(value="/ajaxShowProductDetails", method = RequestMethod.GET)
	public @ResponseBody
	Object showProductDetails(@RequestParam("item") String item) {
		String result="";
		ArrayList<Product> list = new ProductList().getAllProducts();
		for(Product product : list){
			if(product.getName().equalsIgnoreCase(item)){
				result =  product.getName()+","+product.getPrice()+","+product.getSize()+","+product.getColor();
				return result;
			}
		}
		return result;
	}
	
	@RequestMapping(value="/showProductList", method = RequestMethod.POST)
	public String goToList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,@ModelAttribute("ProductList")ProductList product,BindingResult result2,ModelMap model){
		security = new CustomerDetails().getSecurity();
		LoginValidator.validate(customer, result);
		if(!result.hasErrors()){
			if(!customer.getUname().equals("")&&!customer.getPassword().equals("") && security.containsKey(customer.getUname()) && security.get(customer.getUname()).equals(customer.getPassword())){
				HttpSession ssn = request.getSession();
				ssn.setAttribute("uname", customer.getUname());
				uname = customer.getUname();
				model.addAttribute("message",customer.getUname());
				return "/cart/showProductList";
			}
		}
		model.addAttribute("addedToCartOrNot","Item is added to cart");
		return "/login";
	}
}
