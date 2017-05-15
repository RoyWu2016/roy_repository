package com.roy.demo.net.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.roy.demo.model.LogInfo;

public class ObjectClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket socket = new Socket("192.168.0.152", 8888);
		ObjectOutputStream oos = new ObjectOutputStream(socket  
                .getOutputStream());  
//		
		LogInfo user = new LogInfo();  
        user.setUserName("梁国俏");  
        user.setOperation("123456");  
        //输入对象, 一定要flush（）   
        oos.writeObject(user);  
        oos.flush();  
         
        oos.close();  
        socket.close();  

	}

}
