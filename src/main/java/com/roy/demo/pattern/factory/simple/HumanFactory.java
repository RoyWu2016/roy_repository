package com.roy.demo.pattern.factory.simple;

public class HumanFactory {
	
	public static Human makeHuman(String type){
        if(type.equals("man")){
            Human man = new Man();
            return man;
        }else if(type.equals("womman")){
            Human woman = new Woman();
            return woman;
        }else{
            System.out.println("生产不出来");
            return null;
        }            
    }

}
