package com.roy.demo.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class StringClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("192.168.0.152", 8888);
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		// 向服务器端发送请求
		String content = "wuyiyihhhhh";
		out.write(content.getBytes());

		// 从服务器端返回信息
		byte[] buffer = new byte[1024];
		int length = in.read(buffer);
		String str = new String(buffer, 0, length);
		System.out.println("string's length: " + str);

		// 关闭流
		in.close();
		out.close();
		socket.close();

	}

}
