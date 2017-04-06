package com.roy.demo.pattern.factory.abstrac;

public class Factory implements IFactory {

	public IProductA createProductA() {
		// TODO Auto-generated method stub
		return new ProductA();
	}

	public IProductB createProductB() {
		// TODO Auto-generated method stub
		return new ProductB();
	}

}
