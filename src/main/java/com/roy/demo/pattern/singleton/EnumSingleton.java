package com.roy.demo.pattern.singleton;
/*
 *使用枚举除了线程安全和防止反射强行调用构造器之外，还提供了自动序列化机制
 *，防止反序列化的时候创建新的对象。因此，Effective Java推荐尽可能地使用枚举来实现单例。 */
/** 
 * 1.从Java1.5开始支持; 
 * 2.无偿提供序列化机制; 
 * 3.绝对防止多次实例化，即使在面对复杂的序列化或者反射攻击的时候; 
 */  
//6.枚举
public class EnumSingleton{
    private EnumSingleton(){}//保证EnumSingleton是单利的关键，不能缺
    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    
    private static enum Singleton{
        INSTANCE;
        
        private EnumSingleton singleton;
        //JVM会保证此方法绝对只调用一次
        private Singleton(){
            singleton = new EnumSingleton();
        }
        public EnumSingleton getInstance(){
            return singleton;
        }
    }
}
