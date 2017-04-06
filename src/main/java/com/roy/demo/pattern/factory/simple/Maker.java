package com.roy.demo.pattern.factory.simple;

public class Maker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Human man = HumanFactory.makeHuman("man");
      man.say();
      Human womman = HumanFactory.makeHuman("womman");
      womman.say();
      
      Human man2 = HumanFactory2.makeHuman(Man.class);
      man2.say();
      Human woman2 = HumanFactory2.makeHuman(Woman.class);
      woman2.say();
	}

}
