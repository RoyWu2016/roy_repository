package com.roy.demo.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StringServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		// 说明服务器启动成功，正在等待客户端请求连接
		System.out.println("Listening...");
		while (true) {
			Socket socket = ss.accept();
			// 说明有客户端请求连接
			System.out.println("Client Connected...");

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] buffer = new byte[100];

			// 返回客户端请求的字符串长度
			int length = in.read(buffer);
			// 返回客户端请求的字符串
			String content = new String(buffer, 0, length);
			System.out.println("read from client: " + content);

			// 向客户端返回该字符串的长度
			int len = content.length();
			out.write(String.valueOf(len).getBytes());

			// 关闭流
			in.close();
			out.close();
			socket.close();
		}

	}

}
