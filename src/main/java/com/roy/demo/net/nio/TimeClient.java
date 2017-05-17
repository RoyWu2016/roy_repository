package com.roy.demo.net.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("192.168.0.152", 8080);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("Now is : " + resp);
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
				socket = null;
			}
		}
	}

}
