package es.deusto.bspq.cinema.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.remote.CMServiceLocator;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public class CMController {
	
	final static Logger logger = Logger.getLogger(CMController.class);

	private CMServiceLocator cmsl;
	
	public CMController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
		cmsl.setServices(args[0], args[1], args[2]);
	}
	
	public List<SessionDTO> getAllSessions() {
		List<SessionDTO> sessions = null;
		try {
			sessions = cmsl.getService().getSessions();
		} catch (RemoteException e) {
			logger.error("Error getting sessions from server.");
		}
		return sessions;
	}
	
	public boolean buyTicket(TicketDTO ticketDTO) {
		boolean ticket = false;
		try {
			ticket = cmsl.getService().buyTickets(ticketDTO);
		} catch (RemoteException e) {
			logger.error("Error buying a ticket.");
		}
		return ticket;
	}
	
	public void exit() {
    	System.exit(0);
    }
	
}
