package com.bitwise.onlineShoppingCart;

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

@Controller
@Scope("request")
public class CartController {

	@Autowired
	CustomerLogin customer;

	private static String uname;
	HashMap<String, String> security;

	@Autowired
	LoginValidator LoginValidator; 

	@InitBinder("CustomerLogin")
	private void initBinder(WebDataBinder binder){
		binder.setValidator(LoginValidator);
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginpage(@ModelAttribute("CustomerLogin")CustomerLogin customer,ModelMap model) {
		return "/login";
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
				return "/showProductList";
			}
		}
		model.addAttribute("addedToCartOrNot","Item is added to cart");
		return "/login";
	}

	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	@ExceptionHandler({allExceptions.class})
	public String addToCart(HttpServletRequest request, HttpServletResponse response,@RequestParam("item") String item,@ModelAttribute("ProductList") ProductList product,BindingResult result,ModelMap model) throws allExceptions{
		uname = (String)request.getSession().getAttribute("uname"); 
		if(item.equalsIgnoreCase("select")){
			System.out.println("1");
			model.addAttribute("addToCartError","Select some value");
		}
		else{
			new CustomerDAO().addToCart(uname,item);
			model.addAttribute("addedToCartOrNot","Item is added to cart");
		}
		return "/showProductList";
	}

	@RequestMapping(value="/showMyCart", method = RequestMethod.POST)
	public String showMyCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,ModelMap model) {
		System.out.println(uname);
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return "/showMyCart";
	}

	@RequestMapping(value="/RemoveSelectedProduct", method = RequestMethod.POST)
	public String removeFromCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ProductList")ProductList product,BindingResult result,ModelMap model) {
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return "/RemoveSelectedProduct";
	}

	@RequestMapping(value="/removeProduct", method = RequestMethod.POST)
	public ModelAndView removeProduct(HttpServletRequest request, HttpServletResponse response,@RequestParam("item") String item,@ModelAttribute("ProductList")ProductList product,BindingResult result,ModelMap model) {
		HttpSession ssn = request.getSession();
		String uname = (String)ssn.getAttribute("uname");
		new CustomerDAO().removeThisItem(uname,item);
		model.addAttribute("deleted","Item is deleted");
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts);
		model.addAttribute("list",listOfProducts);
		return new ModelAndView("RemoveSelectedProduct",model);
	}


	@RequestMapping(value="/placeOrder", method = RequestMethod.POST)
	public String placeOrder(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,ModelMap model) {
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		float totalAmount = new CustomerDAO().getTotalAmount();
		model.addAttribute("list",listOfProducts);
		model.addAttribute("totalAmount",totalAmount);
		return "/placeOrder";
	}

	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logout(ModelMap model) {
		return "/logout";
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

}