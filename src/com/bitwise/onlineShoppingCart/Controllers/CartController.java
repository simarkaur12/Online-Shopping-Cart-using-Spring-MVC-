package com.bitwise.onlineShoppingCart.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.onlineShoppingCart.DAO.CustomerDAO;
import com.bitwise.onlineShoppingCart.Models.CustomerDetails;
import com.bitwise.onlineShoppingCart.Models.CustomerLogin;
import com.bitwise.onlineShoppingCart.Models.LoginValidator;
import com.bitwise.onlineShoppingCart.Models.Product;
import com.bitwise.onlineShoppingCart.Models.ProductList;
import com.bitwise.onlineShoppingCart.exceptionHandling.allExceptions;

@Controller
@Scope("request")
public class CartController {

	@Autowired
	CustomerLogin customer;

	HashMap<String, String> security;

	@Autowired
	LoginValidator LoginValidator; 

	@InitBinder("CustomerLogin")
	private void initBinder(WebDataBinder binder){
		binder.setValidator(LoginValidator);
	}

	@RequestMapping(value="/showProductList", method = RequestMethod.POST)
	public ModelAndView goToList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,@ModelAttribute("ProductList")ProductList product,BindingResult result2,ModelMap model){
		security = new CustomerDetails().getSecurity();
		LoginValidator.validate(customer, result);
		if(!result.hasErrors()){
			if(!customer.getUname().equals("")&&!customer.getPassword().equals("") && security.containsKey(customer.getUname()) && security.get(customer.getUname()).equals(customer.getPassword())){
				HttpSession ssn = request.getSession();
				ssn.setAttribute("uname", customer.getUname());
				model.addAttribute("uname",customer.getUname());
				return new ModelAndView("/cart/showProductList",model);
			}
		}
		model.addAttribute("error","Please Enter Valid Username and Password....!");
		return new ModelAndView("/cart/login",model);
	}
	
	@RequestMapping(value="/addMoreProducts", method = RequestMethod.POST)
	public ModelAndView AddMoreProducts(@ModelAttribute("ProductList")ProductList product,BindingResult result2,ModelMap model){
		return new ModelAndView("/cart/showProductList",model);
	}

	@RequestMapping(value="/showMyCart", method = RequestMethod.POST)
	public ModelAndView showMyCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,ModelMap model) {
		String uname = (String)request.getSession().getAttribute("uname");
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return new ModelAndView("/cart/showMyCart",model);
	}

	@RequestMapping(value="/RemoveSelectedProduct", method = RequestMethod.GET)
	public ModelAndView removeFromCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ProductList")ProductList product,BindingResult result,ModelMap model) {
		String uname = (String)request.getSession().getAttribute("uname");
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return new ModelAndView("/cart/RemoveSelectedProduct",model);
	}

	@RequestMapping(value="/RemoveSelectedProduct", method = RequestMethod.POST)
	public ModelAndView removeProduct(HttpServletRequest request, HttpServletResponse response,@RequestParam("selectedItem") String item,@ModelAttribute("ProductList")ProductList product,BindingResult result,ModelMap model) {
	    String uname = (String)request.getSession().getAttribute("uname");
		new CustomerDAO().removeThisItem(uname,item);
		model.addAttribute("deleted","Item is deleted");
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts);
		model.addAttribute("list",listOfProducts);
		return new ModelAndView("/cart/RemoveSelectedProduct",model);
	}

	@RequestMapping(value="/placeOrder", method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,ModelMap model) {
		String uname = (String)request.getSession().getAttribute("uname");
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		float totalAmount = new CustomerDAO().getTotalAmount();
		model.addAttribute("list",listOfProducts);
		model.addAttribute("totalAmount",totalAmount);
		return new ModelAndView("/cart/placeOrder",model);
	}
}
