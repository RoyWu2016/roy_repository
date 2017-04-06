package com.roy.demo.pattern.singleton;
/*
 * 这种写法仍然使用JVM本身机制保证了线程安全问题；由于SingletonHolder是私有的，
 * 除了getInstance()之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖JDK版本。*/

//5.静态内部类
public class SingletonInterClass {
	
	private static class Holder {
        private static SingletonInterClass singleton = new SingletonInterClass();
    }
     
    private SingletonInterClass(){}
         
    public static SingletonInterClass getInstance(){
        return Holder.singleton;
    }

}
