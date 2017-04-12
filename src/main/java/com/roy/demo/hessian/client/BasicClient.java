package com.roy.demo.hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.roy.demo.hessian.BasicApi;
import com.roy.demo.hessian.User;

public class BasicClient {

	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8080/HessianServer/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
		BasicApi basic = (BasicApi) factory.create(BasicApi.class, url);
		User usr = basic.getUser();
		if(null != usr) {
			System.out.println("Hello:" + basic.getUser().getList().toString());
		}
		System.out.println("Hello:" + basic.hello());
		System.out.println("Hello:" + basic.getUser().getUserName());
		System.out.println("Hello:" + basic.getUser().getPassword());
		basic.setGreeting("HelloGreeting");
		System.out.println("Hello:" + basic.hello());

	}

}
