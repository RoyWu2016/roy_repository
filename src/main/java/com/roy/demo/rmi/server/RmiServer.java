package com.roy.demo.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.roy.demo.rmi.IStusDAO;
import com.roy.demo.rmi.StusDAO;

/* 
 * 自己编写rmi的话 只需三步 前两步针对服务器端 第三步针对客户端 
 * 1.让远程服务实现类继承UnicastRemoteObject  并让远程服务接口继承Remote 
 * 2.使用LocateRegistry.createRegistry注册RMI的服务端口 Naming.rebind绑定远程服务对象 
 * 3.客户端通过Naming.lookup查找远程服务对象 
 *  
 */

public class RmiServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			IStusDAO istusDAO = new StusDAO();
			// System.setProperty("java.rmi.server.hostname", "192.168.38.172");
			// 创建访问rmi的远程端口
			// 启动rmiregister程序
			// Runtime.getRuntime().exec("rmiregistry 4567");
			LocateRegistry.createRegistry(4567);
			// 注册远程服务对象
			Naming.rebind("rmi://192.168.0.152:4567/IStusDAO", istusDAO);
			System.out.println("注册远程服务对象成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
