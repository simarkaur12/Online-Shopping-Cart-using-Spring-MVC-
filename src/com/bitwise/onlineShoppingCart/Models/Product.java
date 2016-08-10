package com.bitwise.onlineShoppingCart.Models;

public class Product {
	
	String name;
	float price;
	String color;
	int sizeInStock;
	
	public Product(){}
	
	public Product(String name, float price, String color, int size) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.sizeInStock = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return sizeInStock;
	}
	public void setSize(int size) {
		this.sizeInStock = size;
	}
}
