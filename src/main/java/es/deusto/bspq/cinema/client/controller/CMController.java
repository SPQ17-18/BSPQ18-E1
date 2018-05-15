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
	/**
	   * This constructs the CMController.
	   * @param args The command line arguments.
	   * @return Nothing.
	   * @throws RemoteExeption
	   */
	public CMController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
		cmsl.setServices(args[0], args[1], args[2]);
	}
	/**
	   * This is for inserting a film.
	   * @param FilmDTO filmDTO Contents attributes regarding to a film.
	   * @return boolean film Returns weather inserting a film
	   * was successful or not.
	   */
	public boolean insertFilm(FilmDTO filmDTO) {
		boolean film = false;
		try {
			film = cmsl.getService().insertFilm(filmDTO);
		} catch (Exception e) {
			logger.error("Error inserting a film.");
		}
		return film;
	}
	/**
	   * This is for inserting a session.
	   * @param SessionDTO sessionDTO Contents attributes regarding to a session.
	   * @return boolean session Returns weather inserting a session was 
	   * successful or not.
	   */
	public boolean insertSession(SessionDTO sessionDTO) {
		boolean session = false;
		try {
			session = cmsl.getService().insertSession(sessionDTO);
		} catch (Exception e) {
			logger.error("Error inserting a session.");
		}
		return session;
	}
	/**
	   * This is for updating a session.
	   * @param SessionDTO sessionDTO Contents attributes regarding to a session.
	   * @return boolean updated Returns weather updating a session was 
	   * successful or not.
	   */
	public boolean updateSession(SessionDTO sessionDTO) {
		boolean updated = false;
		try {
			updated = cmsl.getService().updateSession(sessionDTO);
		} catch (RemoteException e) {
			logger.error("Error updating a session.");
		}
		return updated;
	}
	/**
	   * This is for deleting a session.
	   * @param SessionDTO sessionDTO Contents attributes regarding 
	   * to a session.
	   * @return boolean deleted Returns weather deleting a session was 
	   * successful or not.
	   */
	public boolean deleteSession(SessionDTO sessionDTO) {
		boolean deleted = false;
		try {
			deleted = cmsl.getService().deleteSession(sessionDTO);
		} catch (RemoteException e) {
			logger.error("Error deleting a session.");
		}
		return deleted;
	}
	/**
	   * This is for getting all the sessions.
	   * @param Nothing.
	   * @return List<SessionDTO> sessions Returns weather getting 
	   * sessions from server was successful or not.
	   */
	public List<SessionDTO> getAllSessions() {
		List<SessionDTO> sessions = null;
		try {
			sessions = cmsl.getService().getSessions();
		} catch (RemoteException e) {
			logger.error("Error getting sessions from server.");
		}
		return sessions;
	}
	/**
	   * This is for getting all the films.
	   * @param Nothing.
	   * @return List<FilmDTO> films Returns weather getting 
	   * films from server was successful or not.  .
	   */
	public List<FilmDTO> getAllFilms() {
		List<FilmDTO> films = null;
		try {
			films = cmsl.getService().getFilms();
		} catch (RemoteException e) {
			logger.error("Error getting films from server.");
		}
		return films;
	}
	/**
	   * This is for buying a ticket.
	   * @param TicketDTO ticketDTO Contents attributes 
	   * regarding to a ticket.
	   * @return boolean ticket Returns weather buying a ticket
	   * was successful or not.
	   */
	public boolean buyTicket(TicketDTO ticketDTO) {
		boolean ticket = false;
		try {
			ticket = cmsl.getService().buyTickets(ticketDTO);
		} catch (RemoteException e) {
			logger.error("Error buying a ticket.");
		}
		return ticket;
	}
	/**
	   * This is for identifying a member.
	   * @param String email Email of the member.
	   * @param String password Password of the member.
	   * @return boolean login Returns weather identifying
	   * a member was successful or not.
	   */
	public boolean identifyMember(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginMember(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a member.");
		}
		return login;
	}
	/**
	   * This is for identifying a employee.
	   * @param String email Email of the employee.
	   * @param String password Password of the employee.
	   * @return boolean login Returns weather identifying
	   * a employee was successful or not.
	   */
	public boolean identifyEmployee(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginEmployee(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a employee.");
		}
		return login;
	}
	/**
	   * This is for getting all the members from the server.
	   * @param Nothing.
	   * @return List<MemberDTO> members Returns weather getting 
	   * members from server was successful or not.
	   */
	public List<MemberDTO> getAllMembers() {
		List<MemberDTO> members = null;
		try {
			members = cmsl.getService().getMembers();
		} catch (RemoteException e) {
			logger.error("Error getting members from server.");
		}
		return members;
	}
	/**
	   * This is for canceling a membership.
	   * @param String email Email of the member.
	   * @param String password Password of the member
	   * @return boolean cancel Returns weather canceling
	   * was successful or not.
	   */
	public boolean cancelMembership(String email, String password) {
		boolean cancel = false;
		try {
			cancel = cmsl.getService().cancelMembership(email, password);
		} catch (RemoteException e) {
			logger.error("Error canceling a membership.");
		}
		return cancel;
	}
	/**
	   * This is for updating a member.
	   * @param MemberDTO memberDTO Contents attributes
	   * regarding to a member.
	   * @return boolean updated Returns weather updating
	   * a member was successful or not.
	   */
	public boolean updateMember(MemberDTO memberDTO) {
		boolean updated = false;
		try {
			updated = cmsl.getService().updateMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error updating a member.");
		}
		return updated;
	}
	/**
	   * This is for deleting a member.
	   * @param MemberDTO memberDTO Contents attributes
	   * regarding to a member.
	   * @return boolean deleted Returns weather deleting
	   * a member was successful or not.
	   */
	public boolean deleteMember(MemberDTO memberDTO) {
		boolean deleted = false;
		try {
			deleted = cmsl.getService().deleteMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error deleting a member.");
		}
		return deleted;
	}
	/**
	   * This is for registering a member.
	   * @param MemberDTO memberDTO Contents attributes
	   * regarding to a member.
	   * @return boolean registered Returns weather registering
	   * a member was successful or not.
	   */
	public boolean registerMember(MemberDTO memberDTO) {
		boolean registered = false;
		try {
			registered = cmsl.getService().registerMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error registering a member.");
		}
		return registered;
	}
	/**
	   * This exits current program by terminating running 
	   * Java virtual machine.
	   * @param Nothing .
	   * @return Nothing .
	   */
	public void exit() {
		System.exit(0);
	}
	
}
