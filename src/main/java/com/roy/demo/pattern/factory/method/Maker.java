package com.roy.demo.pattern.factory.method;

import com.roy.demo.pattern.factory.method.Human;

public class Maker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IHumanFactory factory = null;
        // bike
        factory = new WomanFactory();
        Human bike = factory.makeHuman();
        bike.say();

        // bus
        factory = new ManFactory();
        Human bus = factory.makeHuman();
        bus.say();
	}

}
