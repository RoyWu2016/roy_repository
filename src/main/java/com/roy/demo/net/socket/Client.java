package com.roy.demo.net.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("192.168.0.152", 8888);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		// 读取服务端发来的消息
		String msg = in.readUTF();
		System.out.println(msg);
	}

}
