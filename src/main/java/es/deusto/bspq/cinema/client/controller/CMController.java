package es.deusto.bspq.cinema.client.controller;

import java.rmi.RemoteException;

import es.deusto.bspq.cinema.client.remote.CMServiceLocator;

public class CMController {

	private CMServiceLocator cmsl;
	
	public CMController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
//		cmsl.setServices(args[0], args[1], args[2]);
	}
	
	public void exit() {
    	System.exit(0);
    }
	
}
