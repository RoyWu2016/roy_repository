package com.roy.demo.net.socket;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.roy.demo.model.LogInfo;

public class ObjectServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("Listening...");
		while (true) {
			Socket socket = ss.accept();
			// 说明有客户端请求连接
			System.out.println("Client Connected...");

			// 建立输入流
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Object obj = ois.readObject();
			if (obj != null) {
				LogInfo user = (LogInfo) obj;// 把接收到的对象转化为user
				System.out.println("user: " + user.getUserName());
				System.out.println("password: " + user.getOperation());
			}

			ois.close();
			socket.close();
		}
	}

}
