package com.bitwise.onlineShoppingCart.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitwise.onlineShoppingCart.Models.Product;
import com.bitwise.onlineShoppingCart.Models.ProductList;
import com.bitwise.onlineShoppingCart.exceptionHandling.allExceptions;

public class CustomerDAO {
	static ProductList productListObj = new ProductList();
	ArrayList<Product> customerList = productListObj.getCustomerList();
	ArrayList<Product> allproducts = productListObj.getAllProducts();
	HashMap<String, ArrayList<Product>> map = productListObj.getMap();

	public void addToCart(String uname, String item) throws allExceptions{
		System.out.println(item);
		for(Product product : allproducts){
			if(product.getName().equals(item)){
				System.out.println("before :" + product.getSize());
				if(product.getSize()<=0){
					//error   ---  out of stock
					System.out.println("error out of exception");
					throw new RuntimeException();
				}
				else{
					product.setSize(product.getSize()-1);
					customerList.add(product);
					System.out.println("after :" + product.getSize());
				}
			}
		}
		map.put(uname, customerList);
	}

	public void removeThisItem(String uname,String item) {
		for(Product product : allproducts){
			if(product.getName().equals(item)){
				customerList.remove(product);
			}
		}
		map.put(uname, customerList);
	}
	
	public float getTotalAmount() {
		float total=0;
		for(Product product : customerList){
			total = total + product.getPrice();
		}
		return total;
	}

	public ArrayList<Product> getMyCart(String uname) {
		System.out.println(map.get(uname));
		return map.get(uname);
	}

}
