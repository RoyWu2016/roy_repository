package com.roy.demo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.roy.demo.hessian.User;

public interface IStusDAO extends Remote {

	public User findById(String id) throws RemoteException;
}
