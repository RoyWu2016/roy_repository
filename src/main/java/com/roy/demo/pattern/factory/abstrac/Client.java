package com.roy.demo.pattern.factory.abstrac;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFactory factory = new Factory();  
        factory.createProductA().show();  
        factory.createProductB().show(); 
	}

}
