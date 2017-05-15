package com.roy.demo.model;

import java.io.Serializable;

public class LogInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667226775062538433L;

	private String userName;
	
	private String operation;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
