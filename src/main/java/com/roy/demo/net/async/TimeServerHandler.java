package com.roy.demo.net.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
	
	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			
			String currentTime = null;
			String body = null;
			while(true) {
				body = in.readLine();
				if(null == body) {break;}
				System.out.println("The time server receive : " + body);
				currentTime = "QUERY TIME ORDER".equals(body)? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.println(currentTime);
			}
		}catch (Exception e) {
			if(null != in) {
				try{
					in.close();
				}catch (IOException el) {
					el.printStackTrace();
				}
			}
			if(null != out) {
				out.close();
				out = null;
			}
			if(null != socket) {
				try{
					socket.close();
				}catch (IOException el) {
					el.printStackTrace();
				}
				this.socket = null;
			}
		}
		
	}

}
