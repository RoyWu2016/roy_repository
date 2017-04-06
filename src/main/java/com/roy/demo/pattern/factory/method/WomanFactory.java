package com.roy.demo.pattern.factory.method;

public class WomanFactory implements IHumanFactory {

	public Human makeHuman() {
		// TODO Auto-generated method stub
		return new Woman();
	}

}
