package com.roy.demo.pattern.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumSingleton obj1 = EnumSingleton.getInstance();
	    EnumSingleton obj2 = EnumSingleton.getInstance();
	    //输出结果：obj1==obj2?true
	    System.out.println("obj1==obj2?" + (obj1==obj2));
	}

}
