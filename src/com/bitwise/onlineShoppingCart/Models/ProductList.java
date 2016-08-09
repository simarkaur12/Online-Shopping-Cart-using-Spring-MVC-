package com.bitwise.onlineShoppingCart.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {
	
	ArrayList<Product> allProducts = new ArrayList<Product>();
	String selectedItem;
	ArrayList<Product> customerList = new ArrayList<Product>();
	HashMap<String, ArrayList<Product>> map = new HashMap<String, ArrayList<Product>>();
	
	public ProductList(){
		allProducts.add(new Product("Car",2000000,"Red",5));
		allProducts.add(new Product("Mobile",50000,"Black",20));
		allProducts.add(new Product("Watch",30000,"White",50));
		allProducts.add(new Product("Bentley",385000,"Red",1));
	}
	ArrayList<Product> getAllProducts(){
		return allProducts;
	}
	public String getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	public ArrayList<Product> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(ArrayList<Product> customerList) {
		this.customerList = customerList;
	}
	public HashMap<String, ArrayList<Product>> getMap() {
		// TODO Auto-generated method stub
		return map;
	}
}
