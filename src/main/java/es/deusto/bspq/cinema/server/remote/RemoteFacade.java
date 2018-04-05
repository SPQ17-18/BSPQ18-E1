package es.deusto.bspq.cinema.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

	
	protected RemoteFacade() throws RemoteException {
		
	}

	private static final long serialVersionUID = 1L;

}
