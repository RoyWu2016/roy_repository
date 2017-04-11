package com.roy.demo.jms.activemq.topic;

import java.io.Serializable;

public class MqBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1066927654925652093L;

	private Integer age;

	private String name;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
