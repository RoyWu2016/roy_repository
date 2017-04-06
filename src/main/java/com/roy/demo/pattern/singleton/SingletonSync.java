package com.roy.demo.pattern.singleton;
/*
 * 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，但是，遗憾的是，效率很低，
 * 99%情况下不需要同步。 在Android源码中使用的该单例方法有：InputMethodManager，
 * AccessibilityManager等都是使用这种单例模式。*/
//2.懒汉式变种,加同步锁
public class SingletonSync {
	
	private static volatile SingletonSync instance = null;
	  
    private SingletonSync(){}//單例模式的關鍵，不能缺
    
    public static SingletonSync getInstance() {    
    	 synchronized (Singleton.class) {  
             if (instance == null) {  
                 instance = new SingletonSync();  
             }  
         }  
         return instance;  
    }    

}
