package es.deusto.bspq.cinema.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.remote.CMServiceLocator;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public class CMController {
	
	final static Logger logger = Logger.getLogger(CMController.class);

	private CMServiceLocator cmsl;
	
	public CMController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
		cmsl.setServices(args[0], args[1], args[2]);
	}
	
	public boolean insertFilm(FilmDTO filmDTO) {
		boolean film = false;
		try {
			film = cmsl.getService().insertFilm(filmDTO);
		} catch (Exception e) {
			logger.error("Error inserting a film.");
		}
		return film;
	}
	
	public boolean insertSession(SessionDTO sessionDTO) {
		boolean session = false;
		try {
			session = cmsl.getService().insertSession(sessionDTO);
		} catch (Exception e) {
			logger.error("Error inserting a session.");
		}
		return session;
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
	
	public boolean identifyMember(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginMember(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a member.");
		}
		return login;
	}
	
	public boolean identifyEmployee(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginEmployee(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a member.");
		}
		return login;
	}
	
	public List<MemberDTO> getAllMembers() {
		List<MemberDTO> members = null;
		try {
			members = cmsl.getService().getMembers();
		} catch (RemoteException e) {
			logger.error("Error getting members from server.");
		}
		return members;
	}
	
	public boolean cancelMembership(String email, String password) {
		boolean cancel = false;
		try {
			cancel = cmsl.getService().cancelMembership(email, password);
		} catch (RemoteException e) {
			logger.error("Error canceling a membership.");
		}
		return cancel;
	}
		
	public boolean updateMember(MemberDTO memberDTO) {
		boolean updated = false;
		try {
			updated = cmsl.getService().updateMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error updating a member.");
		}
		return updated;
	}
	
	public boolean deleteMember(MemberDTO memberDTO) {
		boolean deleted = false;
		try {
			deleted = cmsl.getService().deleteMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error deleting a member.");
		}
		return deleted;
	}
	
	public boolean registerMember(MemberDTO memberDTO) {
		boolean registered = false;
		try {
			registered = cmsl.getService().registerMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error registering a member.");
		}
		return registered;
	}
	
	public void exit() {
    	System.exit(0);
    }
	
}
