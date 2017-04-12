package com.roy.demo.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.roy.demo.hessian.User;

public class StusDAO extends UnicastRemoteObject implements IStusDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5132560431440947673L;

	public StusDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public User findById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		User usr = new User("Roy", "1234");
		return usr;
	}

}
