package com.roy.demo.hessian.server;

import com.roy.demo.hessian.BasicApi;
import com.roy.demo.hessian.User;

public class BasicService implements BasicApi {

	private String _greeting = "Hello, world";

	public User getUser() {
		return new User("prance", "meshow");

	}

	public String hello() {
		return _greeting;

	}

	public void setGreeting(String greeting) {
		_greeting = greeting;
		System.out.println("set greeting success:" + _greeting);
	}

}
