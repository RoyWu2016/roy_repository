package com.roy.demo.pattern.singleton;
/*
 * 
 * 这种方法貌似很完美的解决了上述效率的问题，它或许在并发量不多，
 * 安全性不太高的情况能完美运行，但是，这种方法也有不幸的地方。问题就是出现在这句
 * instance = new Singleton(); jdk1.5 以上*/
//3.懒汉式变种,双重检验锁 (Double Check Locking) (DCL)
public class SingletonDcl {

	private static SingletonDcl instance = null;

	private SingletonDcl() {
	}// 單例模式的關鍵，不能缺

	/* 只在第一次初始化的时候加上同步锁 */
	public static SingletonDcl getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new SingletonDcl();
				}
			}
		}
		return instance;
	}

}
