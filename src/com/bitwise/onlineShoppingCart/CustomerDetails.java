package com.bitwise.onlineShoppingCart;

import java.util.HashMap;

public class CustomerDetails {
	
	static HashMap<String, String> security = new HashMap<>();
	
	public CustomerDetails() {
		// TODO Auto-generated constructor stub
	    security.put("simar", "simar");
	    security.put("abc", "abc");
	    security.put("aaa", "aaa");
	}
	
	HashMap<String, String> getSecurity(){
		return security;
	}
	
	
}
