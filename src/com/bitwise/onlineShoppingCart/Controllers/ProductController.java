package com.bitwise.onlineShoppingCart.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwise.onlineShoppingCart.DAO.CustomerDAO;
import com.bitwise.onlineShoppingCart.Models.Product;
import com.bitwise.onlineShoppingCart.Models.ProductList;
import com.bitwise.onlineShoppingCart.exceptionHandling.allExceptions;

@Controller
public class ProductController {
	
	@RequestMapping(value="/showProductList", method = RequestMethod.GET)
	@ExceptionHandler({allExceptions.class})
	public String addToCart(HttpServletRequest request, HttpServletResponse response,@RequestParam("selectedItem") String item,@ModelAttribute("ProductList") ProductList product,BindingResult result,ModelMap model) throws allExceptions{
		String uname = (String)request.getSession().getAttribute("uname"); 
		if(item.equalsIgnoreCase("select")) {
			model.addAttribute("addToCartError","Select some value");
		}
		else{
			new CustomerDAO().addToCart(uname,item);
			model.addAttribute("addedToCartOrNot","Item is added to cart");
		}
		return "/cart/showProductList";
	}
	
	@RequestMapping(value="/ajaxShowProductDetails", method = RequestMethod.GET)
	public @ResponseBody
	Object showProductDetails(@RequestParam("item") String item) {
		String result="";
		ArrayList<Product> list = new ProductList().getAllProducts();
		for(Product product : list){
			if(product.getName().equalsIgnoreCase(item)){
				result =  product.getName()+","+product.getPrice()+","+product.getSizeInStock()+","+product.getColor();
				return result;
			}
		}
		return result;
	}
	
}
