package com.bitwise.onlineShoppingCart.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {
	
    static ArrayList<Product> allProducts = new ArrayList<Product>();
	String selectedItem;
	ArrayList<Product> customerList = new ArrayList<Product>();
	HashMap<String, ArrayList<Product>> map = new HashMap<String, ArrayList<Product>>();
	
	public ProductList(){
		
	}
	public ProductList(ArrayList<Product> allProducts){
		super();
		this.allProducts = allProducts;
	}
	
	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}
	public void setAllProducts(ArrayList<Product> allProducts) {
		this.allProducts = allProducts;
	}
	public void setMap(HashMap<String, ArrayList<Product>> map) {
		this.map = map;
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
