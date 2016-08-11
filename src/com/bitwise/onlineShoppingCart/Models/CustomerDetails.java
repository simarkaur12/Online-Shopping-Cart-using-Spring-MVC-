package com.bitwise.onlineShoppingCart.Models;

import java.util.HashMap;

public class CustomerDetails {
	
	static HashMap<String, String> security = new HashMap<>();
	
	public CustomerDetails() {
	    
	}

	public static HashMap<String, String> getSecurity() {
		return security;
	}

	public static void setSecurity(HashMap<String, String> security) {
		CustomerDetails.security = security;
	}
	
}
