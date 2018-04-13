package es.deusto.bspq.cinema.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public interface IRemoteFacade extends Remote{	
	public ArrayList<SessionDTO> getSessions() throws RemoteException;
	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException;
}
