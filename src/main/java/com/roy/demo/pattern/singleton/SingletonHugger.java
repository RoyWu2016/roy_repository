package com.roy.demo.pattern.singleton;
/*
 * 这种写法不会出现并发问题，但是它是饿汉式的，在ClassLoader加载类后Kerrigan的实例就会第一时间被创建，
 * 饿汉式的创建方式在一些场景中将无法使用：譬如实例的创建是依赖参数或者配置文件的，
 * 在getInstance()之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了*/
//4.饿汉式
public class SingletonHugger {

	private static SingletonHugger instance = new SingletonHugger();

	private SingletonHugger() {
	}// 單例模式的關鍵，不能缺

	public static SingletonHugger getInstance() {
		return instance;
	}

}
