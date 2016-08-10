package com.bitwise.onlineShoppingCart.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.onlineShoppingCart.DAO.CustomerDAO;
import com.bitwise.onlineShoppingCart.Models.CustomerLogin;
import com.bitwise.onlineShoppingCart.Models.Product;
import com.bitwise.onlineShoppingCart.Models.ProductList;
import com.bitwise.onlineShoppingCart.exceptionHandling.allExceptions;

@Controller
@Scope("request")
public class CartController {

	private static String uname;
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	@ExceptionHandler({com.bitwise.onlineShoppingCart.exceptionHandling.allExceptions.class})
	public String addToCart(HttpServletRequest request, HttpServletResponse response,@RequestParam("item") String item,@ModelAttribute("ProductList") ProductList product,BindingResult result,ModelMap model) throws allExceptions{
		uname = (String)request.getSession().getAttribute("uname"); 
		if(item.equalsIgnoreCase("select")){
			model.addAttribute("addToCartError","Select some value");
		}
		else{
			new CustomerDAO().addToCart(uname,item);
			model.addAttribute("addedToCartOrNot","Item is added to cart");
		}
		return "/cart/showProductList";
	}

	@RequestMapping(value="/showMyCart", method = RequestMethod.POST)
	public String showMyCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("CustomerLogin")CustomerLogin customer,BindingResult result,ModelMap model) {
		System.out.println(uname);
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return "/cart/showMyCart";
	}

	@RequestMapping(value="/RemoveSelectedProduct", method = RequestMethod.POST)
	public String removeFromCart(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("ProductList")ProductList product,BindingResult result,ModelMap model) {
		ArrayList<Product> listOfProducts = new CustomerDAO().getMyCart(uname);
		System.out.println(listOfProducts.size());
		model.addAttribute("list",listOfProducts);
		return "/cart/RemoveSelectedProduct";
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
		return "/cart/placeOrder";
	}

	
}
