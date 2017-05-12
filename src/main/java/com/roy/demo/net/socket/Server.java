package com.roy.demo.net.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(8888);
		// 说明服务器成功启动，正在等待客户端连接
		System.out.println("Listening...");
		while (true) {
			Socket socket = ss.accept();
			// 说明有客户端请求连接
			System.out.println("Client Connected...");
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("我是服务器端数据");
			out.close();
			socket.close();
		}

	}

}
