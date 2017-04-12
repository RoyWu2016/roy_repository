package com.roy.demo.hessian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String userName = "snoopy";
	
	String password = "showme";
	
	List<String> list = new ArrayList<String>();

	public User(String user, String pwd) {
		this.userName = user;
		this.password = pwd;
		this.list.add("Roy");
		this.list.add("Tommy");
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
