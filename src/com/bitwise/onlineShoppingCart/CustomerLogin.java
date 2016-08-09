package com.bitwise.onlineShoppingCart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CustomerLogin {

	String uname;
	String password;
	
	public CustomerLogin(){}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CustomerLogin(String uname, String password) {
		super();
		this.uname = uname;
		this.password = password;
	}
}
