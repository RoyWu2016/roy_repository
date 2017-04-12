package com.roy.demo.rmi.client;

import java.rmi.Naming;

import com.alibaba.fastjson.JSON;
import com.roy.demo.hessian.User;
import com.roy.demo.rmi.IStusDAO;

public class RmiClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IStusDAO iStusDAO = (IStusDAO) Naming.lookup("rmi://192.168.0.152:4567/IStusDAO");
			User usr = iStusDAO.findById("1");
			System.out.println(JSON.toJSONString(usr));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
